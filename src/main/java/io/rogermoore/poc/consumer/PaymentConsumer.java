package io.rogermoore.poc.consumer;

import io.rogermoore.poc.domain.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger( PaymentConsumer.class );

    @KafkaListener( topics = "${kafka.topic.payment}", groupId = "${kafka.group-id}" )
    public void receive( Payment payload ) {
        LOGGER.info( "received payload='{}'", payload.toString() );
    }

}
