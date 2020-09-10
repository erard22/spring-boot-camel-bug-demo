# spring-boot-camel-bug-demo
Shows a possible [Bug in Camel](https://issues.apache.org/jira/browse/CAMEL-15486) when using it together with spring-cloud-contract-wiremock

As soon the dependency 

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-contract-wiremock</artifactId>
            <version>2.2.3.RELEASE</version>
            <scope>test</scope>
        </dependency>
```

gets added to the project the mocking of camel endpoints doesn't work anymore.

### Workaround
As workaround you can use a ExecutionListener todo the job and add it to your test class:

```java
@TestExecutionListeners(listeners = {MyTestExecutionListener.class, CamelSpringBootExecutionListener.class }, 
                        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
```

```java
public class MyTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        SpringCamelContext.setNoStart(true);
        System.setProperty("skipStartingCamelContext", "true");
    }
}
```

