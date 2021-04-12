package br.com.autogain.repository;

import br.com.autogain.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends GenericRepository<Balance> {
}
