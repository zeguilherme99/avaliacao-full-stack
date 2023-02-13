package br.com.nava.backend.service;

import br.com.nava.backend.dto.BankAccountDTO;
import br.com.nava.backend.entity.BankAccount;
import br.com.nava.backend.entity.User;
import br.com.nava.backend.repository.BankAccountRepository;
import br.com.nava.backend.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    UserService userService;

    @Override
    public void createBankAccount(Integer userId, BankAccount bankAccount) {
        User user = userService.getUserById(userId);
        bankAccount.setUser(user);
        bankAccountRepository.save(bankAccount);
        user.getBankAccounts().add(bankAccount);
        userService.updateUser(user);
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        BankAccount updatedBankAccount = getBankAccountById(bankAccount.getId());
        updateData(updatedBankAccount, bankAccount);
        bankAccountRepository.save(updatedBankAccount);
    }

    @Override
    public void deleteBankAccount(Integer bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountById(Integer bankAccountId) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);
        return bankAccountOptional.orElseThrow(() -> new ObjectNotFoundException("Bank account", bankAccountId));
    }

    @Override
    public List<BankAccount> getBankAccountsByUser(Integer userId) {
        return bankAccountRepository.findBankAccountsByUserId(userId);
    }

    public void updateData(BankAccount update, BankAccount source) {
        update.setBalance(source.getBalance());
    }

    public BankAccount fromDTO(BankAccountDTO bankAccountDTO) {
        return new BankAccount(bankAccountDTO.getId(), bankAccountDTO.getBalance());
    }


}
