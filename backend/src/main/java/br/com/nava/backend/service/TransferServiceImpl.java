package br.com.nava.backend.service;

import br.com.nava.backend.dto.TransferDTO;
import br.com.nava.backend.entity.BankAccount;
import br.com.nava.backend.entity.Transfer;
import br.com.nava.backend.repository.BankAccountRepository;
import br.com.nava.backend.repository.TransferRepository;
import br.com.nava.backend.service.exceptions.InvalidDateTimeException;
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

    @Autowired
    private BankAccountService bankAccountService;


    @Override
    public void createTransfer(Integer sourceAccountId, Integer targetAccountId, Transfer transfer) {
        Double amount = transfer.getAmount();
        LocalDate today = LocalDate.now();
        LocalDate scheduledDate = transfer.getScheduledDate();
        Double rate = getRate(today, scheduledDate, amount);

        if (rate != null) {
            if (bankAccountRepository.existsById(sourceAccountId) || bankAccountRepository.existsById(targetAccountId)) {
                if (bankAccountRepository.existsById(sourceAccountId)) {
                    if (bankAccountRepository.existsById(targetAccountId)) {
                        Double totalAmount = amount + rate;
                        BankAccount sourceAccount = bankAccountService.getBankAccountById(sourceAccountId);
                        BankAccount targetAccount = bankAccountService.getBankAccountById(targetAccountId);
                        if (verifyAccountBalance(totalAmount, sourceAccount.getBalance())) {
                            Double newSourceAccountBalance = sourceAccount.getBalance() - totalAmount;
                            sourceAccount.setBalance(newSourceAccountBalance);
                            transfer.setTotalAmount(totalAmount);
                            transfer.setToday(today);
                            transfer.setRate(rate);
                            if (!today.isEqual(scheduledDate)) {
                                transfer.setStatus("scheduled");
                            } else {
                                Double newTargetAccountBalance = targetAccount.getBalance() + totalAmount;
                                targetAccount.setBalance(newTargetAccountBalance);
                                transfer.setStatus("concluded");
                                targetAccount.getTransfers().add(transfer);
                                bankAccountRepository.save(targetAccount);
                            }
                            bankAccountRepository.save(sourceAccount);
                            sourceAccount.getTransfers().add(transfer);
                            transferRepository.save(transfer);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer fromDTO(TransferDTO transferDTO) {
        return new Transfer(transferDTO.getId(), transferDTO.getToday(), transferDTO.getScheduledDate(), transferDTO.getAmount());
    }

    private Double getRate(LocalDate today, LocalDate scheduledDate, Double amount) {
        int dateDiff = Period.between(today, scheduledDate).getDays();

        if (dateDiff < 0) {
            throw new InvalidDateTimeException();
        }

        if (dateDiff == 0 && amount <= 1000.00) {
            return amount * 0.03 + 3.00;
        }

        if (dateDiff <= 10 && amount > 1000.00 && amount <= 2000.00) {
            return 12.00;
        }

        if (dateDiff > 10 && amount > 2000.00) {
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
