package com.myproject.banking.controller;

import com.myproject.banking.dto.AccountDto;
import com.myproject.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
       AccountDto accountDto= accountService.getAccountById(id);
       return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{Id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long Id, @RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
        AccountDto dt= accountService.deposit(Id,amount);
        return  ResponseEntity.ok(dt);
    }
    @PutMapping("/{Id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long Id, @RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
        AccountDto dt= accountService.withdraw(Id,amount);
        return  ResponseEntity.ok(dt);
    }
    @GetMapping
    public ResponseEntity<List<AccountDto>> findAllAccounts(){
        List<AccountDto> accountDtos=accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long Id){
        accountService.deleteAccount(Id);
        return ResponseEntity.ok("Account is deleted");
    }

}
