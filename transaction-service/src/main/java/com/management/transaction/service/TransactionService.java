package com.management.transaction.service;

import com.management.transaction.entity.Receipt;
import com.management.transaction.entity.Transaction;
import com.management.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ReceiptService receiptService;

    public Transaction save(Transaction transaction) {
        transaction.setActive(Boolean.TRUE);
        transaction.setTransactionDate(LocalDateTime.now());
        Transaction transactionUpdated = transactionRepository.save(transaction);
        Receipt receipt = new Receipt();
        receipt.setTransactionId(transaction.getTransactionID());
        receipt.setActive(Boolean.TRUE);
        receipt.setStudentId(transaction.getStudentId());
        receiptService.save(receipt);
        return transactionUpdated;
    }

    public List<Transaction> getAll() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }
    
    public Optional<Transaction> get(Long id) {
        return transactionRepository.findById(id);
    }
    
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
