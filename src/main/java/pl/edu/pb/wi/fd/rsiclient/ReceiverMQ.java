package pl.edu.pb.wi.fd.rsiclient;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReceiverMQ {
    private final RabbitTemplate rabbitTemplate;

    public ReceiverMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("get")
    public String get() {
        return Optional.ofNullable(rabbitTemplate.receiveAndConvert("MyMessages"))
                .map(Object::toString)
                .orElse("No more messages.");
    }
}
