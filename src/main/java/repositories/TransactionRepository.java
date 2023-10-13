package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
