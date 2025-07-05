package com.myproject.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountDto implements Serializable {
    private Long id;
    private String accountHolderName;
    private double balance;
}
//public record AccountDto(Long id, String accountHolderName, double balance) {
//}
