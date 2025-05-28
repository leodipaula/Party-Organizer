package github.com.leodipaula.partyorganizer.messaging;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import github.com.leodipaula.partyorganizer.dto.WhatsAppRequest;
import github.com.leodipaula.partyorganizer.service.WhatsAppClient;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@Component
public class WhatsAppReactiveKafkaConsumer {

    private final KafkaReceiver<String, WhatsAppRequest> kafkaReceiver;
    private final WhatsAppClient client;
    private final ReactiveRedisTemplate<String, String> redis;

    public WhatsAppReactiveKafkaConsumer(ReceiverOptions<String, WhatsAppRequest> receiverOptions,
            WhatsAppClient client, ReactiveRedisTemplate<String, String> redis) {
        this.kafkaReceiver = KafkaReceiver.create(receiverOptions);
        this.client = client;
        this.redis = redis;

        startConsuming();
    }

    private void startConsuming() {
        kafkaReceiver.receive().flatMap(record -> {
            WhatsAppRequest request = record.value();
            return client.sendMessage(request.phoneNumber(), request.message()).flatMap(
                    response -> redis.opsForValue().set("msg:" + request.phoneNumber(), "sent"))
                    .onErrorResume(err -> redis.opsForValue().set("msg:" + request.phoneNumber(),
                            "failed:" + err.getMessage()))
                    .thenReturn(record); // devolve o record para o acknowledge
        }).doOnNext(record -> record.receiverOffset().acknowledge())
                .doOnError(err -> System.err.println("Erro ao consumir Kafka: " + err)).subscribe();
    }
}
