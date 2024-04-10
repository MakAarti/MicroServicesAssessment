package com.management.transaction.controller;

import com.management.transaction.entity.Transaction;
import com.management.transaction.exception.TransactionException;
import com.management.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
iimport static org.mockito.Mockito.*;

public class TransactionControllerTest {
  
  @InjectMocks
  TransactionController transactionController;

  @Mock
  TransactionService transactionService;
  
  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testFindTransactionById() throws TransactionException {
    Transaction transaction = new Transaction();
    when(transactionService.get(anyLong())).thenReturn(Optional.of(transaction));
    ResponseEntity<?> response = transactionController.findTransactionById(1L);
    assertEquals( expected: 200, response.getStatuscodeValue());
  }
  
  @Test
  public void testFindALL() {
    List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
    when(transactionService.getAll()).thenReturn(transactions);
    List<Transaction> response = transactionController.findAll();
    assertEquals(2, response.size());
  }
  
  @Test
  public void testCreateTransactionWithReceipt() throws TransactionException {
    Transaction transaction = new Transaction();
    when(transactionService.save(any(Transaction.class))).thenReturn(transaction);
    ResponseEntity<Transaction> response = transactionController.createTransactionWithReceipt(transaction);
    assertEquals(201, response.getStatusCodeValue());
  }
}
