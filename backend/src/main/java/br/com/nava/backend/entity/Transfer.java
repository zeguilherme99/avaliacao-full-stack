package br.com.nava.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "tb_transfer")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    private LocalDate today;
    private LocalDate scheduledDate;
    private Double amount;
    private Double rate;
    private Double totalAmount;
    private String status;

    public Transfer(Integer id, LocalDate today, LocalDate scheduledDate, Double amount) {
        this.id = id;
        this.today = today;
        this.scheduledDate = scheduledDate;
        this.amount = amount;
    }
}
