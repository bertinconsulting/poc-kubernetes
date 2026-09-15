package com.bertinconsulting.poc_kubernetes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Message> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return repository.save(message);
    }
}
