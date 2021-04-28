package br.com.autogain.service;

import br.com.autogain.domain.BaseEntity;
import br.com.autogain.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService<T extends BaseEntity>  {

    @Autowired
    protected GenericRepository<T> genericRepository;

    public List<T> findAll() {
        return genericRepository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return genericRepository.findAll(sort);
    }

    public <S extends T> List<S> saveAll(Iterable<S> var1) {
        return genericRepository.saveAll(var1);
    }

    public <S extends T> S save(S var1) {
        return  genericRepository.save(var1);
    };

    public void delete(String id) {genericRepository.deleteById(id);
    }

    public T getOne(String var1) {
        return genericRepository.findById(var1).get();
    }

    public <S extends T> List<S> findAll(Example<S> var1) {
        return genericRepository.findAll(var1);
    }

    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return genericRepository.findAll(var1, var2);
    }
}
