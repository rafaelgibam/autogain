package br.com.autogain.repository;

import br.com.autogain.domain.Balance;
import com.github.fabiomaffioletti.firebase.repository.DefaultFirebaseRealtimeDatabaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepository extends BaseRepository<Balance, String> {
}
