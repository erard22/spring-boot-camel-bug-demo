package ch.erard22.demo.camel;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

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
