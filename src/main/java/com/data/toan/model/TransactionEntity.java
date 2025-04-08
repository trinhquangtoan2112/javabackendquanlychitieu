package com.data.toan.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Transaction")
@Table(name = "tbl_transaction")
public class TransactionEntity extends AbstractEntity {
    @Column(name = "amount")
    private Long amount;
    @Lob
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
    @Column(name = "transaction_date")
    @CreationTimestamp
    private Date date;
    @Column(name = "create_At")
    @CreationTimestamp
    private Date create_At;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;
}
