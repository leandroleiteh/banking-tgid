package com.lebanking.api.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime timestamp;

    private BigDecimal amount;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Company company;
}
