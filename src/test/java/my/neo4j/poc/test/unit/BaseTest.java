package my.neo4j.poc.test.unit;

import my.neo4j.poc.model.BankAccount;
import my.neo4j.poc.model.Contact;
import my.neo4j.poc.model.Person;
import my.neo4j.poc.repository.AccountRepository;
import my.neo4j.poc.repository.ContactRepository;
import my.neo4j.poc.repository.PersonRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// making spring to reload the context after each test method in order to simulate the scenario where the data
// is just being loaded from a different context than the one that save it
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BaseTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ContactRepository contactRepository;

    private static Long accountId;

    @Test
    public void addData() {
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        personRepository.deleteAll();

        Person person = new Person();
        person.setFirstname("John");
        person.setLastname("Doe");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(100);

        // saving relationships and entities
        Contact contact = contactRepository.save(new Contact(bankAccount, person));

        assertThat(size(contactRepository.findAll()), is(1));
        assertThat(size(accountRepository.findAll()), is(1));
        assertThat(size(personRepository.findAll()), is(1));

        assertNotNull(contact.getBankAccount().getId());
        accountId = contact.getBankAccount().getId();
    }

    @Test
    public void relationshipTest() throws Exception {
        assertNotNull(accountId);

        // When using the BOLT driver the relationship fails to return even though it exists, message is
        //    'Relationship (.)-[HAS_CONTACT]->(.) cannot be hydrated because one or more required node types are not
        //     mapped to entity classes'
        Iterable<Contact> contacts = contactRepository.findByAccountId(accountId);

        // these assertions will succeed for both the BOLT and the HTTP drivers
        assertThat(size(contactRepository.findAll()), is(1));
        assertThat(size(personRepository.findAll()), is(1));
        assertThat(size(accountRepository.findAll()), is(1));
        // assert that contact relationship exists on graph for accountId
        assertThat(accountRepository.findOne(accountId).getContacts().size(), is(1));

        // this assertion will Fail for the BOLT driver, however, it will Succeed for the HTTP driver
        // if the accountRepository.findOne(accountId) statement is executed before calling
        // contactRepository.findByAccountId(accountId) then the test will also succeed for the BOLT driver
        assertThat(size(contacts), is(1));
    }

    private int size(Iterable<?> iterable) {
        int i = 0;
        if (iterable != null) {
            for (Iterator<?> iterator = iterable.iterator(); iterator.hasNext(); iterator.next()) {
                i++;
            }
        }
        return i;
    }
}
