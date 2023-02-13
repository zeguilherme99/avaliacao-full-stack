package br.com.nava.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "balance")
@Getter @Setter
@Table(name = "tb_bank_account")
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public BankAccount(Integer id, Double balance) {
        this.id = id;
        this.balance = balance;
    }
}
