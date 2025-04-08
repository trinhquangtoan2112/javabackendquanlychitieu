package com.data.toan.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Builder
// @NoArgsConstructor
// @AllArgsConstructor
@Entity(name = "Budget")
@Table(name = "tblbudget")
public class BudgetEntity extends AbstractEntity {

}
