package br.com.autogain.repository;

import br.com.autogain.domain.EventMessage;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

@Repository
public class EventMessageRepository extends BaseRepository<EventMessage> {

    public EventMessageRepository(Firestore firestore) {
        super(firestore, "event_messages");
    }

    @SneakyThrows
    public boolean existsByIndex(Long index) {
        Query query = getCollectionReference().whereEqualTo("index", index);
        return !query.get().get().isEmpty() ? true : false;
    };
}
