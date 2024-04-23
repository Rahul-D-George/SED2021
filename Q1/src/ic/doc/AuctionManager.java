package ic.doc;

public class AuctionManager {

    private int highest = 0;
    private final PaymentSystem ps;

    public AuctionManager(PaymentSystem ps) {
        this.ps = ps;
    }

    // Returns true if bid accepted, false if bid was too low.
    public BID_STATUS receiveBid(int amount, Bidder bidder) {
        if (amount > highest) {
            highest = amount;
            ps.charge(amount, bidder);
            return BID_STATUS.BID_ACCEPTED;
        }
        return BID_STATUS.BID_TOO_LOW;
    }


}
