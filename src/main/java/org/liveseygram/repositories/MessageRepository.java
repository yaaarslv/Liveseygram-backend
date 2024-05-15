package org.liveseygram.repositories;

import org.liveseygram.entities.Message;
import org.liveseygram.models.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender_IdAndRecipient_IdOrSender_IdAndRecipient_IdOrderByTimestamp(Long senderId1, Long recipientId1, Long senderId2, Long recipientId2);

    List<Message> findAllBySender_IdOrRecipient_IdOrderByTimestampDesc(Long sender_id, Long recipient_id);

    int countBySenderIdAndRecipientIdAndMessageStatusIs(Long senderId, Long recipientId, MessageStatus messageStatus);
}
