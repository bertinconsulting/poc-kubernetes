package com.bertinconsulting.poc_kubernetes;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository repository;

    private final Counter messagesCreated;

    public MessageController(
            MessageRepository repository,
            MeterRegistry meterRegistry) {

        this.repository = repository;

        this.messagesCreated = Counter.builder("messages.created")
                .description("Number of messages created")
                .register(meterRegistry);
    }

    @GetMapping
    public List<Message> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        messagesCreated.increment();
        return repository.save(message);
    }
}
