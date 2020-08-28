package ch.erard22.demo.camel;

import java.util.Collections;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@CamelSpringBootTest
@SpringBootTest(classes = DemoApplication.class)
@ContextConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints("file:*")
class SimpleCamelRouteTest {

    @Autowired
    private CamelContext camelContext;

    @Produce("direct:processMessage")
    private ProducerTemplate producer;

    @EndpointInject("mock://file:output")
    private MockEndpoint mockCamel;

    @Test
    void processMessage_successful() throws Exception {
        mockCamel.expectedBodiesReceived("foo");
        producer.sendBodyAndHeaders("foo", Collections.emptyMap());
        mockCamel.assertIsSatisfied();
    }
}