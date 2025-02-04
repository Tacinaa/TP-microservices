package org.example.bookservice.service;

import org.example.bookservice.model.Book;
import org.example.bookservice.repository.BookRepository;
import org.example.common.KafkaEvent;
import org.example.common.KafkaTopics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final KafkaProducerService kafkaProducerService;

    public BookService(BookRepository bookRepository, KafkaProducerService kafkaProducerService) {
        this.bookRepository = bookRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

        KafkaEvent event = new KafkaEvent("BOOK_DELETED", id.toString());
        kafkaProducerService.sendEvent(KafkaTopics.BOOK_DELETED, event);
    }
}