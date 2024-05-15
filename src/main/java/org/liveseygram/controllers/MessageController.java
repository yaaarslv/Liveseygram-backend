package org.liveseygram.controllers;

import org.liveseygram.models.ChatWithLastMessage;
import org.liveseygram.entities.Message;
import org.liveseygram.models.MessageToFront;
import org.liveseygram.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id) {
        Optional<Message> message = messageService.getById(id);

        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Message>> findAll() {
        List<Message> messages = messageService.findAll();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createUser(@RequestBody Message message) {
        Message createdUser = messageService.save(message);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateUser(@RequestBody Message message) {
        Message updatedUser = messageService.update(message);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteByEntity")
    public ResponseEntity<Void> deleteUserByEntity(@RequestBody Message message) {
        messageService.deleteByEntity(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        messageService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getChatMessages")
    public ResponseEntity<List<MessageToFront>> getChatMessages(@RequestBody Map<String, Long> requestBody) {
        Long sender_id = requestBody.get("sender_id");
        Long recipient_id = requestBody.get("recipient_id");

        return ResponseEntity.ok(messageService.getChatMessages(sender_id, recipient_id));
    }

    @PostMapping("/getUserChatsWithLastMessage")
    public ResponseEntity<List<ChatWithLastMessage>> getUserChatsWithLastMessage(@RequestBody Long requestBody) {

        return ResponseEntity.ok(messageService.getUserChatsWithLastMessage(requestBody));
    }

    @PostMapping("/changeMessageStatusToREAD")
    public void changeMessageStatusToREAD(@RequestBody List<Long> requestBody) {
        for (Long id : requestBody) {
            messageService.changeMessageStatusToREAD(id);
        }
    }
}
