package br.com.nava.backend.dto;

import br.com.nava.backend.entity.BankAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BankAccountDTO {

    private Integer id;
    private Double balance;

    public BankAccountDTO(BankAccount bankAccount) {
        id = bankAccount.getId();
        balance = bankAccount.getBalance();
    }
}
