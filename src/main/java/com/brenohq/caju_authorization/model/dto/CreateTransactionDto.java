package com.brenohq.caju_authorization.model.dto;


import com.brenohq.caju_authorization.model.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDto {

    private Account account;

    private BigDecimal amount;

    private String mcc;

    private String merchant;
}
