package com.lebanking.api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String cnpj;

    private String contact;

    private BigDecimal balance;

    private Fee fee;


    public Company(String name, String cnpj, String contact, BigDecimal balance, Fee fee) {
        this.name = name;
        this.cnpj = cnpj;
        this.contact = contact;
        this.balance = balance;
        this.fee = fee;

    }
}
