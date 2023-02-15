package br.com.nava.backend.repository;

import br.com.nava.backend.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
