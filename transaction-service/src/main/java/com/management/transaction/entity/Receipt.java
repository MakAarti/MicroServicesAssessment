package com.management.transaction.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "receipt")
@Entity
public class Receipt implements Serializable {

    @Column(name = "receipt_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "transaction_id")
    private Long transactionId;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", transactionId=" + transactionId +
                ", active=" + active +
                '}';
    }
}
