package com.company.controller;

import com.company.dto.AccountDTO;
import com.company.dto.request.CreateAccountRequestDTO;
import com.company.dto.request.MoneyTransferRequest;
import com.company.dto.request.UpdateAccountRequest;
import com.company.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<AccountDTO> createAccount(
            @Valid @RequestBody CreateAccountRequestDTO createAccountRequestDTO
    ) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccountsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }


}

