package br.com.nava.backend.controller;

import br.com.nava.backend.dto.BankAccountDTO;
import br.com.nava.backend.entity.BankAccount;
import br.com.nava.backend.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/bank_accounts")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccountDTO> findAll() {
        List<BankAccount> bankAccounts= bankAccountService.getBankAccounts();
        return bankAccounts.stream().map(BankAccountDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountDTO findById(@PathVariable Integer id) {
        BankAccount bankAccount = bankAccountService.getBankAccountById(id);
        return new BankAccountDTO(bankAccount);
    }

    @PostMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@PathVariable Integer userId, @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountService.fromDTO(bankAccountDTO);
        bankAccountService.createBankAccount(userId, bankAccount);
    }

    @PutMapping(value = "/{bankAccountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer bankAccountId, @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountService.fromDTO(bankAccountDTO);
        bankAccount.setId(bankAccountId);
        bankAccountService.updateBankAccount(bankAccount);
    }

    @DeleteMapping(value = "/{bankAccountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer bankAccountId) {
        bankAccountService.deleteBankAccount(bankAccountId);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccount> findBankAccountsByUserId(@RequestParam("user") String userId) {
        Integer user = Integer.parseInt(userId);
        return bankAccountService.getBankAccountsByUser(user);
    }
}
