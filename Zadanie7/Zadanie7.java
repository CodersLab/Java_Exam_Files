import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPriceCalculator {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.23");
    private static final BigDecimal FREE_DELIVERY_THRESHOLD = new BigDecimal("200.00");
    private static final BigDecimal DELIVERY_COST = new BigDecimal("15.00");

    public BigDecimal calculateFinalPrice(BigDecimal netPrice, int quantity, CustomerType customerType) {
        if (netPrice == null) {
            throw new IllegalArgumentException("Net price cannot be null");
        }

        if (customerType == null) {
            throw new IllegalArgumentException("Customer type cannot be null");
        }

        if (netPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Net price must be greater than zero");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        BigDecimal totalNetPrice = netPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal discount = calculateDiscount(totalNetPrice, customerType);
        BigDecimal priceAfterDiscount = totalNetPrice.subtract(discount);
        BigDecimal vat = calculateVat(priceAfterDiscount);
        BigDecimal deliveryCost = calculateDeliveryCost(priceAfterDiscount);

        return priceAfterDiscount
                .add(vat)
                .add(deliveryCost)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateDiscount(BigDecimal totalNetPrice, CustomerType customerType) {
        switch (customerType) {
            case REGULAR:
                return BigDecimal.ZERO;
            case PREMIUM:
                return totalNetPrice.multiply(new BigDecimal("0.05"));
            case VIP:
                return totalNetPrice.multiply(new BigDecimal("0.10"));
            default:
                throw new IllegalArgumentException("Unsupported customer type");
        }
    }

    private BigDecimal calculateVat(BigDecimal priceAfterDiscount) {
        return priceAfterDiscount.multiply(VAT_RATE);
    }

    private BigDecimal calculateDeliveryCost(BigDecimal priceAfterDiscount) {
        if (priceAfterDiscount.compareTo(FREE_DELIVERY_THRESHOLD) >= 0) {
            return BigDecimal.ZERO;
        }

        return DELIVERY_COST;
    }
}

enum CustomerType {
    REGULAR,
    PREMIUM,
    VIP
}