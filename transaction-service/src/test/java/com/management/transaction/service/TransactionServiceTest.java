package com.management.transaction.service;
import com.management.transaction.entity.Receipt;
import com.management.transaction.entity.Transaction;
import com.management.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
  
  @InjectMocks
  TransactionService transactionService;
  
  @Mock
  TransactionRepository transactionRepository;
  
  @Mock
  ReceiptService receiptservice;
  
  @BeforeEach
  public void init() { MockitoAnnotations.initMocks(this); }
  
  @Test
  public void testsave() {
    Transaction transaction = new Transaction();
    Receipt receipt = new Receipt();
    when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
    when(receiptService.save(any(Receipt.class))).thenReturn(receipt);|
    Transaction response = transactionService.save(transaction);
    assertEquals(transaction, response);
  }

  @Test
  public void testGetAZL() {
    List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
    when(transactionRepository.findAll()).thenReturn(transactions);
    List<Transaction> response = transactionService.getAll();
    assertEquals(2, response.size());
  }

  @Test
  public void testGet() {
    Transaction transaction = new Transaction();
    when(transactionRepository.findById(anyLong())).thenReturn(0ptional.of(transaction));
    Optional<Transaction> response = transactionService.get(1L);
    assertTrue(response.isPresent());
    assertEquals(transaction, response.get());
  }
  
  @Test
  public void testDelete() {
    doNothing().when(transactionRepository).deleteById(anyLong());
    assertDoesNotThrow(() -> transactionService.delete(1L));
  }
}
