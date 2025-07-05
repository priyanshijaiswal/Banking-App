package com.myproject.banking.mapper;

import com.myproject.banking.dto.AccountDto;
import com.myproject.banking.entity.Account;

import java.sql.Statement;

public class AccountMapper {
    public static Account  mapToAccount(AccountDto accountDto){
        // Here we have convorted accountdto to account JPA entity
        return new Account(accountDto.id(),
                accountDto.accountHolderName(),accountDto.balance());
    }
    public static AccountDto mapToAccountDto(Account account)
    {
        return new AccountDto(account.getId(),account.getAccountHolderName(),account.getBalance());
    }
}
