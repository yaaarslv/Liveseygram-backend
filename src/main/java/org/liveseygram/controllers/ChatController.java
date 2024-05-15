package org.liveseygram.controllers;

import org.liveseygram.entities.Message;
import org.liveseygram.entities.User;
import org.liveseygram.models.ChatNotification;
import org.liveseygram.models.MessageFromFront;
import org.liveseygram.models.MessageToFront;
import org.liveseygram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.liveseygram.services.MessageService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    private final MessageService messageService;

    private final UserService userService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.userService = userService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageFromFront messageFromFront) {
        Optional<User> sender = userService.findById(messageFromFront.getSender());
        Optional<User> recipient = userService.findById(messageFromFront.getRecipient());
        Message message = new Message(sender.get(), recipient.get(), messageFromFront.getContent(), LocalDateTime.now(ZoneId.of("UTC")).plusHours(3));
        Message saved = messageService.save(message);
        MessageToFront toFront = new MessageToFront(saved.getId(), saved.getSender(), saved.getRecipient(), saved.getContent(), saved.getMessageStatus().toString(), saved.getSender().getAvatarPath());

        messagingTemplate.convertAndSendToUser(
                String.valueOf(messageFromFront.getRecipient()),"/queue/messages", toFront);
    }
}
