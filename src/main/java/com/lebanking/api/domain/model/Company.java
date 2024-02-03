package com.lebanking.api.domain.model;

import com.lebanking.api.domain.model.fee.DepositFee;
import com.lebanking.api.domain.model.fee.FeeStrategy;
import com.lebanking.api.domain.model.fee.WithdrawalFee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

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

    private FeeStrategy withdrawalFeeStrategy = new WithdrawalFee();

    private FeeStrategy depositFeeStrategy = new DepositFee();

    public BigDecimal calculateWithdrawalFee(BigDecimal amount) {
        return withdrawalFeeStrategy.calculateFee(amount);
    }

    public BigDecimal calculateDepositFee(BigDecimal amount) {
        return depositFeeStrategy.calculateFee(amount);
    }
}
