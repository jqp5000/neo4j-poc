package my.neo4j.poc.test.unit;

import my.neo4j.poc.test.BoltDriverConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BoltDriverConfig.class}, loader = SpringApplicationContextLoader.class)
public class BoltDriverTest extends BaseTest {
}
