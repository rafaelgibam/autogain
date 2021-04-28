package br.com.autogain.repository;

import br.com.autogain.domain.EventMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EventMessageRepository extends MongoRepository<EventMessage, String> {
    boolean existsByIndex(BigInteger index);
}
