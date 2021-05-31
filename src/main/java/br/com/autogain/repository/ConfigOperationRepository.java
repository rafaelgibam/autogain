package br.com.autogain.repository;

import br.com.autogain.domain.ConfigOperation;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigOperationRepository extends BaseRepository<ConfigOperation> {

    protected ConfigOperationRepository(Firestore firestore) {
        super(firestore, "config_operations");
    }
}
