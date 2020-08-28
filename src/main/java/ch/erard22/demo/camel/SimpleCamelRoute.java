package ch.erard22.demo.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleCamelRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:processMessage")
                .to("file:output");
    }
}
