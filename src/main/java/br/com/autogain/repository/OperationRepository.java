package br.com.autogain.repository;

import br.com.autogain.domain.Operation;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import com.github.fabiomaffioletti.firebase.repository.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationRepository extends BaseRepository<Operation, String> {

    public List<Operation> findBySignalId(String signalId) {
        Filter filter = Filter.FilterBuilder.builder().equalTo(signalId).build();
        return find(filter);
    }

}
