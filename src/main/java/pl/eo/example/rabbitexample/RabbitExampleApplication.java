package pl.eo.example.rabbitexample;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class RabbitExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitExampleApplication.class, args);
	}

	@Bean public FanoutExchange exchange() {
		return new FanoutExchange("pub-sub-test");
	}

	@Bean public Queue channel() throws IOException {
		return QueueBuilder.nonDurable().autoDelete().exclusive().build();
	}

	@Bean public Binding firstBinding(Queue queue, Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("routnig").noargs();
	}

	@Bean SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter, Queue queue) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(listenerAdapter);
		container.setQueues(queue);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageConsumer receiver) {
		return new MessageListenerAdapter(receiver, new SimpleMessageConverter());
	}

}
