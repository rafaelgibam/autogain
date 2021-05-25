package br.com.autogain.repository;

import br.com.autogain.domain.Operation;
import br.com.autogain.domain.Signal;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SignalRepository extends BaseRepository<Signal, String> {
}
