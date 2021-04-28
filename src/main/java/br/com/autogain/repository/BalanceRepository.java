package br.com.autogain.repository;

import br.com.autogain.domain.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends MongoRepository<Balance, String> {
}
