import java.math.BigDecimal;

public class OrderEvent {

    private final Long orderId;
    private final String customerEmail;
    private final BigDecimal totalAmount;

    public OrderEvent(Long orderId, String customerEmail, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}