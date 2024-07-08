package com.brenohq.caju_authorization.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "mcc", nullable = false)
    private String mcc;

    @Column(name = "merchant", nullable = false)
    private String merchant;

    public Transaction(String accountId, BigDecimal amount, String mcc, String merchant) {
        this.accountId = accountId;
        this.amount = amount;
        this.mcc = mcc;
        this.merchant = merchant;
    }

}
