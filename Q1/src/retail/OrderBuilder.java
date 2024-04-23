package retail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderBuilder {

    protected List<Product> items = new ArrayList<>();
    protected CreditCardDetails creditCardDetails;
    protected Address billingAddress;
    protected Address shippingAddress;
    protected Courier courier;
    protected BigDecimal discount;
    private boolean giftWrap = false;
    private ChargingMethod cm;


    public Order build() {

        if (shippingAddress == null) {shippingAddress = billingAddress;}

        if (items.size() > 3) {
            if (giftWrap) {throw new RuntimeException("Big order cannot have a gift-wrap.");}
            return new BulkOrder(items, creditCardDetails, billingAddress, shippingAddress, courier, discount, cm);
        } else {
            if (discount != null) {throw new RuntimeException("Small order cannot have a discount.");}
            return new SmallOrder(items, creditCardDetails, billingAddress, shippingAddress, courier, giftWrap, cm);
        }
    }

    public OrderBuilder withItem(Product p) {
        items.add(p); return this;
    }

    public OrderBuilder withCCDs(CreditCardDetails ccd) {
        creditCardDetails = ccd; return this;
    }

    public OrderBuilder withBillingAddress(Address address) {
        billingAddress = address; return this;
    }

    public OrderBuilder withShippingAddress(Address address) {
        shippingAddress = address; return this;
    }

    public OrderBuilder withCourier(Courier c) {
        courier = c; return this;
    }

    public OrderBuilder withDiscount(BigDecimal d) {
        discount = d; return this;
    }

    public OrderBuilder withGiftWrap(boolean b) {
        giftWrap = b; return this;
    }

    public OrderBuilder withChargingMethod(ChargingMethod cm) {
        this.cm = cm; return this;
    }

}
