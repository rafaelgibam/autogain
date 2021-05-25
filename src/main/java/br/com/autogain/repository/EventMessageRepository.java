package br.com.autogain.repository;

import br.com.autogain.domain.EventMessage;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class EventMessageRepository extends BaseRepository<EventMessage, String> {
    public boolean existsByIndex(BigInteger index) {
        Filter filter = Filter.FilterBuilder.builder().equalTo(index).build();
        return !find(filter).isEmpty() ? true : false;
    };
}
