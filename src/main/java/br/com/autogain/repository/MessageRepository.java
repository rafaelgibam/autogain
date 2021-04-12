package br.com.autogain.repository;

import br.com.autogain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends GenericRepository<Message> {
}
