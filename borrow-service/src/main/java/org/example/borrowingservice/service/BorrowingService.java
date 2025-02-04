package org.example.borrowingservice.service;

import org.example.borrowingservice.model.BorrowingRecord;
import org.example.borrowingservice.repository.BorrowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public List<BorrowingRecord> getAllBorrows() {
        return borrowingRepository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowById(Long id) {
        return borrowingRepository.findById(id);
    }

    public List<BorrowingRecord> getBorrowedByUser(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }

    public BorrowingRecord saveBorrow(BorrowingRecord borrowingRecord) {
        return borrowingRepository.save(borrowingRecord);
    }

    public void deleteBorrow(Long id) {
        borrowingRepository.deleteById(id);
    }
}