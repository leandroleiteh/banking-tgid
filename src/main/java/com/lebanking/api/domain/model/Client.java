package com.lebanking.api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
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

    public Client(String name, String cpf, Date birthDate, String tel, String mail, BigDecimal balance) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.tel = tel;
        this.mail = mail;
        this.balance = balance;
    }

}
