package com.management.transaction.controller;

import com.management.transaction.entity.Transaction;
import com.management.transaction.service.TransactionService;
import com.management.transaction.exception.TransactionException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Tag(name="get", description = "GET methods of Transaction APIs")
    @GetMapping("/transaction/{id}")
    public ResponseEntity<?> findTransactionById(@PathVariable("id") Long id) throws TransactionException {
        Optional<Transaction> transaction = transactionService.get(id);
        if(transaction.isPresent())
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Transaction not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Tag(name="get", description = "GET methods of Transaction APIs")
    @GetMapping("/transactions")
    public List<Transaction> findAll() throws TransactionException {
        return transactionService.getAll();
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransactionWithReceipt(@RequestBody Transaction transaction) throws TransactionException {
        return new ResponseEntity<>(
                transactionService.save(transaction),
            HttpStatus.CREATED);

    }

}