package com.lebanking.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String cpf;

    @NonNull
    private Date birthDate;

    @NonNull
    private String tel;

    @NonNull
    private String mail;

    @NonNull
    private BigDecimal balance = BigDecimal.ZERO;

    public Client(String name, String cpf, Date date, String tel, String mail, BigDecimal balance) {
    }
}
