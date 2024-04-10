package com.management.transaction.controller;

import com.management.transaction.entity.Receipt;
import com.management.transaction.exception.TransactionException;
import com.management.transaction.model.ReceiptDTO;
import com.management.transaction.service.ReceiptService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/receipt")
    public ResponseEntity<Receipt> saveReceipt(@RequestBody Receipt receipt) throws TransactionException {
        return new ResponseEntity<>(
                receiptService.save(receipt),
                HttpStatus.CREATED);
    }

    @CircuitBreaker(name = "getReceiptByTransactionId", fallbackMethod = "fallbackDefaultReceipt")
    @GetMapping("/receipt/{transactionId}")
    public ResponseEntity<ReceiptDTO> getReceiptByTransactionId(@PathVariable("transactionId") Long transactionId) {
        return new ResponseEntity<>(receiptService.getReceiptByTransactionId(transactionId), HttpStatus.OK);
    }
}