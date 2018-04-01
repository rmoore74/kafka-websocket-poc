package io.rogermoore.poc.producer;

import io.rogermoore.poc.domain.Payment;
import io.rogermoore.poc.domain.type.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger( PaymentProducer.class );

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.payment}")
    private String topic;

    @RequestMapping( value = "/pay", method = RequestMethod.POST )
    public ResponseEntity<String> makePayment(@RequestBody Payment payment) throws Exception {
        LOGGER.info( "Payment='{}', status='{}'", payment.getPaymentId(), payment.getStatus() );
        kafkaTemplate.send( topic, payment.getPaymentId() + " : " + payment.getStatus() );
        Thread.sleep( 1000 );

        payment.setStatus(PaymentStatus.PAYMENT_RECEIVED);
        LOGGER.info( "Payment='{}', status='{}'", payment.getPaymentId(), payment.getStatus() );
        kafkaTemplate.send( topic, payment.getPaymentId() + " : " + payment.getStatus() );
        Thread.sleep( 1000 );

        payment.setStatus(PaymentStatus.PAYMENT_PROCESSING);
        LOGGER.info( "Payment='{}', status='{}'", payment.getPaymentId(), payment.getStatus() );
        kafkaTemplate.send( topic, payment.getPaymentId() + " : " + payment.getStatus() );
        Thread.sleep( 1000 );

        payment.setStatus(PaymentStatus.PAYMENT_PROCESSED);
        LOGGER.info( "Payment='{}', status='{}'", payment.getPaymentId(), payment.getStatus() );
        kafkaTemplate.send( topic, payment.getPaymentId() + " : " + payment.getStatus() );
        Thread.sleep( 1000 );

        payment.setStatus(PaymentStatus.COMPLETE);
        LOGGER.info( "Payment='{}', status='{}'", payment.getPaymentId(), payment.getStatus() );
        kafkaTemplate.send( topic, payment.getPaymentId() + " : " + payment.getStatus() );
        Thread.sleep( 1000 );

        return new ResponseEntity<String>("Making payment of " + payment.getAmount() + " to " + payment.getRecipient(), HttpStatus.OK );
    }

}
