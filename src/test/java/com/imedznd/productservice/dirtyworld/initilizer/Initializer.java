package com.imedznd.productservice.dirtyworld.initilizer;

import lombok.SneakyThrows;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.testcontainers.containers.GenericContainer;

@Configuration
public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @SneakyThrows
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final GenericContainer mongoContainer =
                new GenericContainer<>("mongo")
                        .withExposedPorts(27017, 27017);
        System.out.println("starting mongo container = " + mongoContainer.getHost());
        mongoContainer.start();
        while (!mongoContainer.isRunning()) {
            System.out.println("starting  ...");
            Thread.sleep(3000);
        }
        if (mongoContainer.isRunning())
            System.out.println("mongoContainer is running");
        TestPropertyValues
                .of(
                        "spring.data.mongodb.host=" + mongoContainer.getHost(),
                        "spring.data.mongodb.host.port=" + mongoContainer.getMappedPort(27017)
                )
                .applyTo(applicationContext.getEnvironment());
        final var applicationContextCloseListener = new ApplicationListener<ContextClosedEvent>() {

            @SneakyThrows
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                mongoContainer.stop();
                while (mongoContainer.isRunning())
                    Thread.sleep(3000);
            }
        };

        applicationContext
                .addApplicationListener(applicationContextCloseListener);

    }
}
