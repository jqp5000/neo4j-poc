package my.neo4j.poc.repository;

import my.neo4j.poc.model.BankAccount;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends GraphRepository<BankAccount> {
}
