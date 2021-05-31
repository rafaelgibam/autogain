package br.com.autogain.repository;

import br.com.autogain.domain.Balance;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepository extends BaseRepository<Balance> {
    protected BalanceRepository(Firestore firestore) {
        super(firestore, "balances");
    }
}
