# spring-boot-camel-bug-demo
Shows a possible Bug in Camel when using it together with spring-cloud-contract-wiremock

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
