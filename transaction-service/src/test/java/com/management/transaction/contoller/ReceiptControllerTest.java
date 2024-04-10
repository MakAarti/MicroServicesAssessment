package com.management.transaction.controller;
import com.management.transaction.controller.ReceiptController;
import com.management.transaction.entity.Receipt;
import com.management.transaction.exception.TransactionException;
import com.management.transaction.model.ReceiptDTO;
import com.management.transaction.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong
import static org.mockito.Mockito.when;

public class ReceiptControllerTest {
  @InjectMocks
  ReceiptController receiptController;
  
  @Mock
  ReceiptService receiptService;
  
  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testSaveReceipt() throws TransactionException {
    Receipt receipt = new Receipt();
    when(receiptService.save(any(Receipt.class))).thenReturn(receipt);
    ResponseEntity<Receipt> response = receiptController.saveReceipt(receipt);
    assertEquals(201, response.getStatusCodeValue());
  }
  
  @Test
  public void testGetReceiptByTransactionId() {
    ReceiptDTO receiptDTO = new ReceiptDT@();
    when(receiptService.getReceiptByTransactionId(anyLong())).thenReturn(receiptDTO);
    ResponseEntity<ReceiptDTO> response= receiptController.getReceiptByTransactionId(1L);
    assertEquals(200, response.getStatus@odeValue());
  }
}
