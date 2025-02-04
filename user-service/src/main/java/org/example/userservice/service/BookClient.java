package org.example.userservice.service;

import org.example.userservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", contextId = "bookClient")
public interface BookClient {
    @GetMapping("/books/user/{userId}")
    List<Book> getBooksByUserId(@PathVariable Long userId);
}
