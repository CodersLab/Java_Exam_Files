import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    private PaymentProcessor paymentProcessor;
    private CustomerNotifier customerNotifier;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        paymentProcessor = mock(PaymentProcessor.class);
        customerNotifier = mock(CustomerNotifier.class);
        orderService = mock(OrderService.class); // A
    }

    @Test
    void shouldProcessPaymentAndNotifyCustomer() {
        String customerId = "customer-123";
        BigDecimal amount = new BigDecimal("149.99"); // B

        orderService.placeOrder(customerId, amount);

        verify(paymentProcessor).processPayment(customerId, amount); // C
        verify(customerNotifier).notifyCustomer(customerId, "Order has been placed successfully"); // D
    }
}