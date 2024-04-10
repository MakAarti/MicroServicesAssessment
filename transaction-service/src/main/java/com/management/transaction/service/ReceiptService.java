package com.management.transaction.service;

import com.management.student.entity.Student;
import com.management.transaction.entity.Receipt;
import com.management.transaction.entity.Transaction;
import com.management.transaction.model.ReceiptDTO;
import com.management.transaction.model.StudentDTO;
import com.management.transaction.model.TransactionDTO;
import com.management.transaction.repository.ReceiptRepository;
import com.management.transaction.repository.TransactionRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    public ReceiptService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public ReceiptDTO getReceiptByTransactionId(Long transactionId) {
        Receipt receipt = receiptRepository.findByTransactionId(transactionId);

        Application application = eurekaClient.getApplication("student-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);

        String studentServiceUrl = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "api/v1/student/" + receipt.getStudentId();

        Student student = restTemplate.getForObject(studentServiceUrl, Student.class);
        ReceiptDTO receiptDTO = new ReceiptDTO();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getId());
        studentDTO.setStudentName(student.getName());
        studentDTO.setGrade(student.getGrade());
        studentDTO.setSchoolName(student.getSchoolName());
        studentDTO.setMobile(student.getMobile());

        TransactionDTO transactionDTO = new TransactionDTO();
        Optional<Transaction> transaction = transactionRepository.findById(receipt.getTransactionId());
        transactionDTO.setCardNumber(transaction.get().getCardNumber());
        transactionDTO.setCardType(transaction.get().getCardType());
        transactionDTO.setStudentName(student.getName());
        transactionDTO.setStudentId(student.getId());
        transactionDTO.setTransactionDate(transaction.get().getTransactionDate());
        transactionDTO.setReference(transaction.get().getTransactionID());
        
        receiptDTO.setStudent(studentDTO);
        receiptDTO.setTransaction(transactionDTO);
        receiptDTO.setEmailNote("This is an automated email, please do not reply. For any other query, please email us at contactus@skiply.ae");
        return receiptDTO;
    }
}
