# neo4j-poc
This is a basic poc using Spring Boot and Neo4j to illustrate an inconsistency found between the bolt and http drivers. 

The tests consist in running the exact same code each with a different driver.

The only pre-requisite is that you will need to have Neo4j 3 installed with the Bolt connector enabled.

When running 'mvn clean install' 2/3 test will fail:
    my.neo4j.poc.test.unit.BoltDriverTest will FAIL
    my.neo4j.poc.test.unit.EmbeddedDriverTest will FAIL
    my.neo4j.poc.test.unit.HttpDriverTest will SUCCEED
