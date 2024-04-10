package com.management.transaction.repository;

import com.management.transaction.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Receipt findByTransactionId(@Param("transactionId") Long transactionId);
}
