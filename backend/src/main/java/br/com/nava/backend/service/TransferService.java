package br.com.nava.backend.service;

import br.com.nava.backend.dto.TransferDTO;
import br.com.nava.backend.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferService {



    void createTransfer(Integer sourceAccountId, Integer targetAccountId, Transfer transfer);
    List<Transfer> getTransfers();

    Transfer fromDTO(TransferDTO transferDTO);

}
