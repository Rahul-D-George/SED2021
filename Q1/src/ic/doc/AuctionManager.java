package ic.doc;

import java.util.ArrayList;
import java.util.List;

public class AuctionManager {

    private int highest = 0;
    private final PaymentSystem ps;
    private final Seller seller;
    private final Item item;

    private final List<Bidder> previousBidders;
    private final List<Integer> previousAmounts;

    public AuctionManager(PaymentSystem ps, Seller seller1, Item item1) {
        this.ps = ps;
        this.seller = seller1;
        this.item = item1;
        this.previousBidders = new ArrayList<>();
        this.previousAmounts = new ArrayList<>();
    }

    // Returns true if bid accepted, false if bid was too low.
    public BID_STATUS receiveBid(int amount, Bidder bidder) {
        if (amount > highest) {
            previousBidders.add(bidder);
            previousAmounts.add(amount);
            highest = amount;
            ps.charge(amount, bidder);
            return BID_STATUS.BID_ACCEPTED;
        }
        return BID_STATUS.BID_TOO_LOW;
    }


}
