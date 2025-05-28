package github.com.leodipaula.partyorganizer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import github.com.leodipaula.partyorganizer.dto.WhatsAppRequest;
import github.com.leodipaula.partyorganizer.messaging.WhatsAppProducer;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/proxy/whatsapp")
public class WhatsAppProxyController {

    private final WhatsAppProducer producer;

    public WhatsAppProxyController(WhatsAppProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity<String>> send(@RequestBody WhatsAppRequest request) {
        return producer.send(request)
                .map(result -> ResponseEntity.ok("Mensagem enviada para Kafka"));
    }
}

