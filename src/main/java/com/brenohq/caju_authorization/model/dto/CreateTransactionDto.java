package com.brenohq.caju_authorization.model.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDto {

    private String accountId;

    private BigDecimal amount;

    private String mcc;

    private String merchant;
}
