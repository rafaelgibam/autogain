package br.com.autogain.repository;

import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalRepository extends MongoRepository<Signal, String> {
}
