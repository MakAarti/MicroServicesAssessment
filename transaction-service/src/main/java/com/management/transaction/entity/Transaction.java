package com.management.transaction.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "transaction")
@Entity
public class Transaction implements Serializable {

    @Column(name = "transaction_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;


    private Long tuitionFee;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String cardType;
    private String cardNumber;

    private Boolean active;

    @Transient
    private Long studentId;

    public enum PaymentMethod {
        CASH, CARD
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", tuitionFee=" + tuitionFee +
                ", transactionDate=" + transactionDate +
                ", paymentMethod=" + paymentMethod +
                ", cardType='" + cardType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", active=" + active +
                ", studentId=" + studentId +
                '}';
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Long tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
