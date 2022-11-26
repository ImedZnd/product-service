package com.imedznd.productservice.dirtyworld.initilizer;

import lombok.SneakyThrows;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @SneakyThrows
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

//        final GenericContainer mongoContainer =
//                new GenericContainer<>("mongo")
//                        .withExposedPorts(27017, 27017);
        mongoDBContainer.start();
        while (!mongoDBContainer.isRunning()) {
            System.out.println("starting  ...");
            Thread.sleep(3000);
        }
        System.out.println("starting mongo container = " + mongoDBContainer.getHost());
        System.out.println("starting mongo container = " + mongoDBContainer.getConnectionString());

        if (mongoDBContainer.isRunning())
            System.out.println("mongoContainer is running");
        TestPropertyValues
                .of(
                        "spring.data.mongodb.uri=" + mongoDBContainer.getConnectionString()
                )
                .applyTo(applicationContext.getEnvironment());
        final var applicationContextCloseListener = new ApplicationListener<ContextClosedEvent>() {

            @SneakyThrows
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                mongoDBContainer.stop();
                while (mongoDBContainer.isRunning())
                    Thread.sleep(3000);
            }
        };

        applicationContext
                .addApplicationListener(applicationContextCloseListener);

    }
}
