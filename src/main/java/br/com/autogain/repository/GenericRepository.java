package br.com.autogain.repository;

import br.com.autogain.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T extends BaseEntity> extends MongoRepository<T, Long> {
}
