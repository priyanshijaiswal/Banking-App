package com.myproject.banking.service.impl;

import com.myproject.banking.dto.AccountDto;
import com.myproject.banking.entity.Account;
import com.myproject.banking.mapper.AccountMapper;
import com.myproject.banking.repository.AccountRepository;
import com.myproject.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long Id) {
        Account account=accountRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("Account does not exixts"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long Id, double amount) {
        Account account=accountRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("Account does not exixts"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account=accountRepository.findById(Id).
                orElseThrow(()->new RuntimeException("Account does not exists"));
        if(amount>account.getBalance())
        {
            throw new RuntimeException("Insufficient account balance");
        }
        double am=account.getBalance()-amount;
        account.setBalance(am);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map(a->AccountMapper.mapToAccountDto(a)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long Id) {
        Account account=accountRepository.findById(Id).
                orElseThrow(()->new RuntimeException("Account does not exists"));
        accountRepository.deleteById(Id);

    }
}
