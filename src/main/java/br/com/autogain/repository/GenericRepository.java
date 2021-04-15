package br.com.autogain.repository;

import br.com.autogain.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
