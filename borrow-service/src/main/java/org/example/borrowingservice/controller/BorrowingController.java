package org.example.borrowingservice.controller;

import org.example.borrowingservice.model.BorrowingRecord;
import org.example.borrowingservice.service.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrow")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<BorrowingRecord> getAllBorrows() {
        return borrowingService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowById(@PathVariable Long id) {
        Optional<BorrowingRecord> borrowRecord = borrowingService.getBorrowById(id);
        return borrowRecord.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<BorrowingRecord> getBorrowedByUser(@PathVariable Long userId) {
        return borrowingService.getBorrowedByUser(userId);
    }

    @PostMapping
    public BorrowingRecord saveBorrow(@RequestBody BorrowingRecord borrowingRecord) {
        return borrowingService.saveBorrow(borrowingRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        if (!borrowingService.getBorrowById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        borrowingService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }
}