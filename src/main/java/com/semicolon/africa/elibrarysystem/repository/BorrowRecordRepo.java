package com.semicolon.africa.elibrarysystem.repository;

import com.semicolon.africa.elibrarysystem.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, UUID> {
  Optional <BorrowRecord> findBorrowRecordById(UUID id);
}
