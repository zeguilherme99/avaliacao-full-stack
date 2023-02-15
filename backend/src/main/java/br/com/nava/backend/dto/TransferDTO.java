package br.com.nava.backend.dto;

import br.com.nava.backend.entity.Transfer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class TransferDTO implements Serializable {

    private Integer id;
    private LocalDate today;
    private LocalDate scheduledDate;
    private Double amount;
    private Double rate;
    private Double totalAmount;
    private String status;

    public TransferDTO(Transfer transfer) {
        id = transfer.getId();
        today = transfer.getToday();
        scheduledDate = transfer.getScheduledDate();
        totalAmount = transfer.getTotalAmount();
        status = transfer.getStatus();
    }
}
