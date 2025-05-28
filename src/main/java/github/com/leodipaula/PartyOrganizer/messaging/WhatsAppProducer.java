package github.com.leodipaula.partyorganizer.messaging;

import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import github.com.leodipaula.partyorganizer.dto.WhatsAppRequest;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Component
public class WhatsAppProducer {

    private final ReactiveKafkaProducerTemplate<String, WhatsAppRequest> kafkaTemplate;

    public WhatsAppProducer(ReactiveKafkaProducerTemplate<String, WhatsAppRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<SenderResult<Void>> send(WhatsAppRequest request) {
        return kafkaTemplate.send("whatsapp-messages", request.phoneNumber(), request);
    }
}

