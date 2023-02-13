package br.com.nava.backend.service;

import br.com.nava.backend.dto.BankAccountDTO;
import br.com.nava.backend.entity.BankAccount;

import java.util.List;

public interface BankAccountService {

    void createBankAccount(Integer userId, BankAccount bankAccount);
    void updateBankAccount(BankAccount bankAccount);
    void deleteBankAccount(Integer bankAccountId);
    List<BankAccount> getBankAccounts();
    List<BankAccount> getBankAccountsByUser(Integer userId);
    BankAccount getBankAccountById(Integer bankAccountId);
    void updateData(BankAccount update, BankAccount source);
    BankAccount fromDTO(BankAccountDTO bankAccountDTO);
}
