import java.math.BigDecimal;

public class OrderService {

    private final PaymentProcessor paymentProcessor;
    private final CustomerNotifier customerNotifier;

    public OrderService(PaymentProcessor paymentProcessor, CustomerNotifier customerNotifier) {
        this.paymentProcessor = paymentProcessor;
        this.customerNotifier = customerNotifier;
    }

    public void placeOrder(String customerId, BigDecimal amount) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer id cannot be empty");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        paymentProcessor.processPayment(customerId, amount);
        customerNotifier.notifyCustomer(customerId, "Order has been placed successfully");
    }
}

interface PaymentProcessor {

    void processPayment(String customerId, BigDecimal amount);
}

interface CustomerNotifier {

    void notifyCustomer(String customerId, String message);
}