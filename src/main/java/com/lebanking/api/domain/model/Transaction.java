package com.lebanking.api.domain.model;

import com.lebanking.api.domain.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime timestamp;

    private BigDecimal amount;

    private BigDecimal totalAmout;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Transaction(BigDecimal amount, BigDecimal totalAmout, Client client, Company company) {
        this.amount = amount;
        this.client = client;
        this.company = company;
    }
}
