package com.management.transaction.service;
import com.management.student.entity.Student;
import com.management.transaction.entity.Receipt;
import com.management.transaction.entity.Transaction;
import com.management.transaction.model.ReceiptDTO;
import com.management.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import com.management.transaction.repository.ReceiptRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ReceiptServiceTest {
  
  @InjectMocks
  ReceiptService receiptService;
  
  @Mock
  ReceiptRepository receiptRepository;
  
  @Mock
  TransactionRepository transactionRepository;
  
  @Mock
  RestTemplate restTemplate;
  
  @Mock
  EurekaClient eurekaClient;
  
  @Mock
  RestTemplateBuilder restTemplateBuilder;
  
  @BeforeEach
  public void init() { MockitoAnnotations.initMocks( testClass: this); }
  
  @Test
  public void testSave() {
    Receipt receipt = new Receipt();
    when(receiptRepository.save(any(Receipt.class))).thenReturn(receipt);
    Receipt response= receiptService.save(receipt);
    assertEquals(receipt, response);
  }
  
  @Test
  public void testGetReceiptByTransactionId() {
    Receipt receipt = new Receipt();
    receipt.setStudentId(1L);
    receipt.setTransactionId(1L);
    when(receiptRepository.findByTransactionId(anyLong())).thenReturn(receipt);
    Application application = mock(Application.class);
    InstanceInfo instanceInfo = mock(InstanceInfo.class);
    when(eurekaClient.getApplication("student-service")).thenReturn(application);
    when(application.getInstances()).thenReturn(Arrays.asList(instanceInfo));
    when(instanceInfo.getIPAddr()).thenReturn(t "localhost");
    when(instanceInfo.getPort()).thenReturn(t 8080);
    Student student = new Student();
    student.setId(1L);
    student.setName("John Doe");
    student.setGrade("A");
    student.setSchoolName("XYZ School");
    student.setMobile("1234567890");
    when(restTemplate.getFor0bject(anyString(, eq(Student.class))).thenReturn(student);
    
    Transaction transaction = new Transaction();
    transaction.setCardNumber("1234567890123456");
    transaction.setCardType("VISA");
    transaction.setTransactionDate(LocalDateTime.now());
    transaction.setTransactionID(123456789L);
    when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transaction));
    ReceiptDTO response = receiptService.getReceiptByTransactionId(1L);
    assertEquals("John Doe", response.getStudent().getStudentName());
    assertEquals("1234567890123456", response.getTransaction().getCardNumber());
  }
}
