package br.com.autogain.repository;

import br.com.autogain.domain.Balance;
import br.com.autogain.domain.ConfigOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigOperationRepository extends MongoRepository<ConfigOperation, String> {
}
