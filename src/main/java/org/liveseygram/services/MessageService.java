package org.liveseygram.services;

import org.liveseygram.models.ChatWithLastMessage;
import org.liveseygram.entities.Message;
import org.liveseygram.entities.User;
import org.liveseygram.models.MessageStatus;
import org.liveseygram.models.MessageToFront;
import org.liveseygram.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message entity) {
        return messageRepository.save(entity);
    }

    public void deleteById(long id) {
        messageRepository.deleteById(id);
    }

    public void deleteByEntity(Message entity) {
        messageRepository.delete(entity);
    }

    public void deleteAll() {
        messageRepository.deleteAll();
    }

    public Message update(Message entity) {
        return messageRepository.save(entity);
    }

    public Optional<Message> getById(long id) {
        return messageRepository.findById(id);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<MessageToFront> getChatMessages(Long sender_id, Long recipient_id) {
        List<MessageToFront> resultMessages = new ArrayList<>();
        List<Message> messages = messageRepository.findBySender_IdAndRecipient_IdOrSender_IdAndRecipient_IdOrderByTimestamp(sender_id, recipient_id, recipient_id, sender_id);

        for (Message message : messages) {
            if (!Objects.equals(message.getMessageStatus().toString(), "READ") && Objects.equals(message.getSender().getId(), recipient_id)) {
                changeMessageStatusToREAD(message.getId());
            }

            MessageToFront messageToFront;
            if (Objects.equals(message.getSender().getId(), recipient_id)) {
                messageToFront = new MessageToFront(message.getId(), message.getSender(), message.getRecipient(), message.getContent(), MessageStatus.READ.toString(), message.getRecipient().getAvatarPath());
            } else {
                messageToFront = new MessageToFront(message.getId(), message.getSender(), message.getRecipient(), message.getContent(), message.getMessageStatus().toString(), message.getRecipient().getAvatarPath());
            }
            resultMessages.add(messageToFront);
        }


        return resultMessages;
    }

    public List<ChatWithLastMessage> getUserChatsWithLastMessage(Long user_id) {
        List<ChatWithLastMessage> chats = new ArrayList<>();
        List<Message> allMessages = messageRepository.findAllBySender_IdOrRecipient_IdOrderByTimestampDesc(user_id, user_id);

        if (allMessages.isEmpty()) {
            return chats;
        }

        List<Long> uniqueIdOfParticipants = new ArrayList<>();
        for (Message message : allMessages) {
            User participant = null;


            if (Objects.equals(message.getSender().getId(), user_id)) {
                participant = message.getRecipient();
            } else if (Objects.equals(message.getRecipient().getId(), user_id)) {
                participant = message.getSender();
            }

            if (!uniqueIdOfParticipants.contains(participant.getId())) {
                uniqueIdOfParticipants.add(participant.getId());
                chats.add(new ChatWithLastMessage(participant, message, 0));
            }
        }

        for (ChatWithLastMessage chat : chats) {
            int count = messageRepository.countBySenderIdAndRecipientIdAndMessageStatusIs(chat.getParticipantId(), user_id, MessageStatus.RECEIVED);
            chat.setUnreadMessagesCount(count);
        }

        return chats;
    }

    public void changeMessageStatusToREAD(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            Message mes = message.get();
            mes.setMessageStatus(MessageStatus.READ);
            messageRepository.save(mes);
        }
    }
}

