package io.rogermoore.poc.config;

import io.rogermoore.poc.domain.Payment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class PaymentConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers );
        props.put( ConsumerConfig.GROUP_ID_CONFIG, groupId );
        props.put( ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest" );

        return props;
    }

    @Bean
    public ConsumerFactory<String, Payment> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>( consumerConfigs(), stringKeyDeserializer(), paymentJsonValueDeserializer() );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Payment>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Payment> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory( consumerFactory() );
        return factory;
    }

    @Bean
    public Deserializer stringKeyDeserializer() {
        return new StringDeserializer();
    }

    @Bean
    public Deserializer paymentJsonValueDeserializer() {
        return new JsonDeserializer( Payment.class );
    }

}
