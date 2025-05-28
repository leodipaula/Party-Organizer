package github.com.leodipaula.partyorganizer.config;

import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import github.com.leodipaula.partyorganizer.dto.WhatsAppRequest;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class ReactiveKafkaConsumerConfig {

    @Bean
    public ReceiverOptions<String, WhatsAppRequest> kafkaReceiverOptions(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> props = Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers, ConsumerConfig.GROUP_ID_CONFIG, "party-whatsapp-group",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.TRUSTED_PACKAGES, "*", ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "latest");

        ReceiverOptions<String, WhatsAppRequest> options = ReceiverOptions.create(props);
        return options.subscription(List.of("whatsapp.outgoing"));
    }
}

