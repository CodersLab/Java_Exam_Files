import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderQueueService {

    @RabbitListener(queues = "orders.queue")
    public void sendOrderEvent(OrderEvent orderEvent) {
        System.out.println("Order event sent: " + orderEvent.getOrderId());
    }
}