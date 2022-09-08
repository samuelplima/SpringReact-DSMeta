package com.devsuperior.dsmeta.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sales")
public class Sale {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "visited")
    private Integer visited;

    @Column(name = "deals")
    private Integer deals;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;

}
