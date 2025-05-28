package github.com.leodipaula.partyorganizer.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WhatsAppClient {

        private final WebClient webClient;
        private String phoneNumberId;

        public WhatsAppClient(WebClient.Builder builder,
                        @Value("${whatsapp.api.url}") String baseUrl,
                        @Value("${whatsapp.token}") String token,
                        @Value("${whatsapp.phone-number-id}") String phoneNumberId) {
                this.webClient = builder.baseUrl(baseUrl)
                                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                                .build();
                this.phoneNumberId = phoneNumberId;
        }


        public Mono<String> sendMessage(String phoneNumber, String message) {
                Map<String, Object> body = Map.of("messaging_product", "whatsapp", "to",
                                phoneNumber, "type", "text", "text", Map.of("body", message));

                return webClient.post().uri("/v17.0/" + phoneNumberId + "/messages").bodyValue(body)
                                .retrieve().bodyToMono(String.class);
        }
}

