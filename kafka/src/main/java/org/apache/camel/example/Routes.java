package org.apache.camel.example;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // produces messages to kafka
        from("timer:foo?repeat-Count=2")
                .routeId("FromTimer2Kafka")
                .setBody()
                .simple("Message #${exchangeProperty.CamelTimerCounter}")
                .to("kafka:{{kafka.topic.name}}")
                .log("Message correctly sent to the topic!");

        // kafka consumer
        from("kafka:{{kafka.topic.name}}")
                .to("seda:kafka-results");

    }
}
