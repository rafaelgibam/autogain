package br.com.autogain.repository;

import br.com.autogain.model.EventMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EventMessageRepository extends GenericRepository<EventMessage> {
    boolean existsByIndex(BigInteger index);
}
