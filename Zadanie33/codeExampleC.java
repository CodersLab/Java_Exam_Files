import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderQueueService {

    public void sendOrderEvent(OrderEvent orderEvent) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.convertAndSend("orders.queue", orderEvent);
    }
}