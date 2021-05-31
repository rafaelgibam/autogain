package br.com.autogain.repository;

import br.com.autogain.domain.Operation;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationRepository extends BaseRepository<Operation> {


    OperationRepository(Firestore firestore) {
        super(firestore, "operations");
    }

//    @SneakyThrows
//    public List<Operation> findBySignalId(String signalId) {
//        Query query = getCollectionReference().whereEqualTo("signalId", signalId);
//        return query.get().get().toObjects(Operation.class);
//    }

}
