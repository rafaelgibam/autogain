package br.com.autogain.repository;

import br.com.autogain.domain.Message;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository extends BaseRepository<Message> {
    protected MessageRepository(Firestore firestore) {
        super(firestore, "messages");
    }
}
