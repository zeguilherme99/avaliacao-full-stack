package br.com.nava.backend.dto;

import br.com.nava.backend.entity.BankAccount;
import br.com.nava.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
public class UserDTO implements Serializable {

    private Integer id;

    @Setter
    private String name;

    private List<BankAccount> bankAccounts;

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        bankAccounts = user.getBankAccounts();
    }
}
