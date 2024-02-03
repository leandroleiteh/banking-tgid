package com.lebanking.api.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String cpf;

    private Date birthDate;

    private String tel;

    private String mail;

    private BigDecimal balance;
}
