package ic.doc;

public interface PaymentSystem {
    void charge(int amount, Bidder bidder);
    void pay(int amount, Party person);
}
