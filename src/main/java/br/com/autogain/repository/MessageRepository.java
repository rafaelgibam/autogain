package br.com.autogain.repository;

import br.com.autogain.domain.Message;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository extends BaseRepository<Message, String> {
}
