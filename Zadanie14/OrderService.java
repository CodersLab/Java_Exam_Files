import java.math.BigDecimal;

public class OrderService {

    private final PaymentProcessor paymentProcessor;
    private final CustomerNotifier customerNotifier;

    public OrderService(PaymentProcessor paymentProcessor, CustomerNotifier customerNotifier) {
        this.paymentProcessor = paymentProcessor;
        this.customerNotifier = customerNotifier;
    }

    public void placeOrder(String customerId, BigDecimal orderAmount) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer id cannot be empty");
        }

        if (orderAmount == null || orderAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Order amount must be greater than zero");
        }

        String hashedCustomerId = hashCustomerId(customerId);

        paymentProcessor.processPayment(hashedCustomerId, orderAmount);
        customerNotifier.notifyCustomer(hashedCustomerId, "Order has been placed successfully");
    }

    private String hashCustomerId(String customerId) {
        MagicHasher magicHasher = new MagicHasher();
        return magicHasher.hash(customerId);
    }
}

class MagicHasher {

    public String hash(String value) {
        return "MAGIC-" + value.hashCode() + "-" + System.nanoTime();
    }
}

interface PaymentProcessor {

    void processPayment(String customerId, BigDecimal amount);
}

interface CustomerNotifier {

    void notifyCustomer(String customerId, String message);
}