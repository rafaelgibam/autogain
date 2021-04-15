package br.com.autogain.repository;

import br.com.autogain.domain.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends GenericRepository<Message> {
}
