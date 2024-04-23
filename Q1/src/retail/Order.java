package retail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class Order {

    protected final List<Product> items;
    protected final CreditCardDetails creditCardDetails;
    protected final Address billingAddress;
    protected final Address shippingAddress;
    protected final Courier courier;

    protected Order(List<Product> items, CreditCardDetails creditCardDetails,
                    Address billingAddress, Address shippingAddress, Courier courier) {
        this.items = items;
        this.creditCardDetails = creditCardDetails;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.courier = courier;
    }

    public abstract void process();

    protected BigDecimal round(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.CEILING);
    }

}
