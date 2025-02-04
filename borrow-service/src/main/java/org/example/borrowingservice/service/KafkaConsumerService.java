package org.example.borrowingservice.service;

import org.example.common.KafkaEvent;
import org.example.common.KafkaTopics;
import org.example.borrowingservice.repository.BorrowingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final BorrowingRepository borrowingRepository;

    public KafkaConsumerService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    @KafkaListener(topics = KafkaTopics.BOOK_DELETED, groupId = "borrowing-service")
    public void handleBookDeleted(KafkaEvent event) {
        Long bookId = Long.parseLong(event.getKey());
        borrowingRepository.deleteByBookId(bookId);
    }

    @KafkaListener(topics = KafkaTopics.USER_DELETED, groupId = "borrowing-service")
    public void handleUserDeleted(KafkaEvent event) {
        Long userId = Long.parseLong(event.getKey());
        borrowingRepository.deleteByUserId(userId);
    }
}