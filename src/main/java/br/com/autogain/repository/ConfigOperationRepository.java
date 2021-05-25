package br.com.autogain.repository;

import br.com.autogain.domain.Balance;
import br.com.autogain.domain.ConfigOperation;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigOperationRepository extends BaseRepository<ConfigOperation, String> {
}
