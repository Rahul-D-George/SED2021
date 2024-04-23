package retail;

import java.math.BigDecimal;

public interface ChargingMethod {
    public void charge(BigDecimal amount, CreditCardDetails account, Address billingAddress);
}
