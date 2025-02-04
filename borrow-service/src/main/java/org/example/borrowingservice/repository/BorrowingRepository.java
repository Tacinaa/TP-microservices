package org.example.borrowingservice.repository;

import org.example.borrowingservice.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByUserId(Long userId);

    void deleteByBookId(Long bookId);
    void deleteByUserId(Long userId);
}
