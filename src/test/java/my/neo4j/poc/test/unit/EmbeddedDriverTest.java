package my.neo4j.poc.test.unit;

import my.neo4j.poc.test.EmbeddedDriverConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmbeddedDriverConfig.class}, loader = SpringApplicationContextLoader.class)
public class EmbeddedDriverTest extends BaseTest {

    // Couldn't get the Embedded driver to work, getting
    //          java.lang.NoClassDefFoundError: org/neo4j/helpers/Function
}
