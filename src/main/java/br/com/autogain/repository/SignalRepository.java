package br.com.autogain.repository;

import br.com.autogain.domain.Signal;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

@Repository
public class SignalRepository extends BaseRepository<Signal> {
    protected SignalRepository(Firestore firestore) {
        super(firestore, "signals");
    }
}
