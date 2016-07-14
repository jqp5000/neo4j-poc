package my.neo4j.poc.repository;

import my.neo4j.poc.model.Contact;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends GraphRepository<Contact> {

    @Query("MATCH (a:BankAccount)-[r:HAS_CONTACT]->(:Person) " +
            "WHERE ID(a)={accountId} " +
            "RETURN r")
    Iterable<Contact> findByAccountId(@Param("accountId") Long accountId);
}
