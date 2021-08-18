package pl.eo.example.rabbitexample;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping("/send")
	public void send(@RequestParam("message") String message){
		rabbitTemplate.convertAndSend("pub-sub-test", "routing", message);
	}
}
