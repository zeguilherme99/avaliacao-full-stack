package br.com.nava.backend.service;

import br.com.nava.backend.entity.Transfer;
import br.com.nava.backend.repository.BankAccountRepository;
import br.com.nava.backend.repository.TransferRepository;
import br.com.nava.backend.service.exceptions.InvalidDateTimeException;
import br.com.nava.backend.service.utils.RateType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private RateType rateType;

    @Override
    public Transfer createTransfer(Integer sourceAccountId, Integer targetAccountId, Transfer transfer) {
        double amount = transfer.getAmount();
        LocalDate today = LocalDate.now();
        LocalDate scheduleDate = transfer.getScheduledDate();
        Double rate = getRate(today, scheduleDate, amount);

        if (rate != null) {
            if (bankAccountRepository.existsById(sourceAccountId) || bankAccountRepository.existsById(targetAccountId)) {

            }
        }


    }

    @Override
    public List<Transfer> getTransfers() {
        rateType.
        return null;
    }

    private Double getRate(LocalDate today, LocalDate scheduledDate, Double amount) {
        int dateDiff = Period.between(today, scheduledDate).getDays();

        if (dateDiff < 0) {
            throw new InvalidDateTimeException();
        }

        if (dateDiff == 0 && amount <= 1000) {
            return amount * 0.03 + 3;
        }
        if (dateDiff <= 10 && amount > 1000 && amount <= 2000) {
            return 12.00;
        }
        if (dateDiff > 10 && amount > 2000) {
            if (dateDiff <= 20) {
                return amount * 0.082;
            }
            if (dateDiff <= 30) {
                return amount * 0.069;
            }
            if (dateDiff <= 40) {
                return amount * 0.047;
            }
            return amount * 0.017;
        }
        return null;
    }

    private Boolean verifyAccountBalance(Double amount, Double balance) {
        return amount <= balance;
    }
}
