import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderQueueService {

    private final RabbitTemplate rabbitTemplate;

    public OrderQueueService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {
        rabbitTemplate.receiveAndConvert("orders.queue");
    }
}