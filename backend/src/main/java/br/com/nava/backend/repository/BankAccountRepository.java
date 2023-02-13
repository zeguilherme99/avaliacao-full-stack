package br.com.nava.backend.repository;

import br.com.nava.backend.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findBankAccountsByUserId(Integer userId);
}
