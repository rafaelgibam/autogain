package br.com.autogain.repository;

import br.com.autogain.domain.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findBySignalId(String signalId);

}
