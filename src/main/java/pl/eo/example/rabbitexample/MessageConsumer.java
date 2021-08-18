package pl.eo.example.rabbitexample;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component public class MessageConsumer {

	public void handleMessage(String message) {
		System.out.println("Message *****************" + message);
	}
}
