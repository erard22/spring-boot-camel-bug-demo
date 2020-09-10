package ch.erard22.demo.camel;

import java.util.Collections;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootExecutionListener;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

@CamelSpringBootTest
@SpringBootTest()
@ContextConfiguration
@MockEndpoints("file:*")
@TestExecutionListeners(listeners = {MyTestExecutionListener.class, CamelSpringBootExecutionListener.class }, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class SimpleCamelRouteTest {

    @Autowired
    private CamelContext camelContext;

    @Produce("direct:processMessage")
    private ProducerTemplate producer;

    @EndpointInject("mock:file:output")
    private MockEndpoint mockCamel;

    @AfterEach
    void afterEach() {
        mockCamel.reset();
    }

    @Test
    void processMessage_successful() throws Exception {
        mockCamel.expectedBodiesReceived("foo");
        producer.sendBodyAndHeaders("foo", Collections.emptyMap());
        mockCamel.assertIsSatisfied();
    }


    @Test
    void processMessage_successful2() throws Exception {
        mockCamel.expectedBodiesReceived("foo");
        producer.sendBodyAndHeaders("foo", Collections.emptyMap());
        mockCamel.assertIsSatisfied();
    }
}