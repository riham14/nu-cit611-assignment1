package com.software.architecture;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import com.software.architecture.model.IncidentEvent;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@EnableKafka
@Configuration

public class KafkaConfig {

	@Bean
	public ConsumerFactory<Object, IncidentEvent> consumerFactory() {

		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

		try {
			return new DefaultKafkaConsumerFactory<>(
					config);
					// new JsonDeserializer(),
					// new JsonDeserializer<>(IncidentEvent.class));
		} catch (Exception error) {
			error.printStackTrace();
		}
		return null;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, IncidentEvent> concurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, IncidentEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setMessageConverter(new StringJsonMessageConverter());

		return factory;
	}
}
