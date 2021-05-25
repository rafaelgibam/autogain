package br.com.autogain.repository;

import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, String> extends DefaultFirebaseRealtimeDatabaseRepository<T, String> {

    public T save(T object) {
        return set(object);
    }

    public Optional<T> findById(String id) {
        return Optional.of(get(id));
    }

    public T saveAll(List<T> objects) {
        return saveAll(objects);
    }

    public void deleteById(String id) {
        remove(id);
    }

}
