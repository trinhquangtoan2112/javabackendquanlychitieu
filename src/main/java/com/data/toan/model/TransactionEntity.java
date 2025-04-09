package com.data.toan.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class TransactionEntity extends AbstractEntity implements Serializable {
    @Column(name = "amount")
    private Long amount;

    @Column(name = "note", length = 500)
    private String note;
    @Column(name = "transaction_date")
    @CreationTimestamp
    private Date date;
    @Column(name = "create_At")
    @CreationTimestamp
    private Date create_At;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user_id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoriesEntity category;
}
