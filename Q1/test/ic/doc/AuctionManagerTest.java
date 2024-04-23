package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuctionManagerTest {

  public JUnitRuleMockery context = new JUnitRuleMockery();
  PaymentSystem ps = context.mock(PaymentSystem.class);
  Bidder bidder = context.mock(Bidder.class);
  Item item = context.mock(Item.class);
  Seller seller = context.mock(Seller.class);
  AuctionManager auctionManager = new AuctionManager(ps, seller, item);


  @Test
  public void AuctionsCanBeInitialisedWithInitialSuccessfulBid() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, bidder);
    }});

    BID_STATUS status = auctionManager.receiveBid(10, bidder);
    assertEquals(status, BID_STATUS.BID_ACCEPTED);
  }

  @Test
  public void LowBidsAreRejected() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, bidder);
    }});

    auctionManager.receiveBid(10, bidder);
    BID_STATUS newStatus = auctionManager.receiveBid(5, bidder);
    assertEquals(newStatus, BID_STATUS.BID_TOO_LOW);
  }

  @Test
  public void NewHighestBidsReplaceTheOldAndAreSuccessful() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, bidder);
      oneOf(ps).charge(20, bidder);
    }});

    auctionManager.receiveBid(10, bidder);
    BID_STATUS newStatus = auctionManager.receiveBid(20, bidder);
    assertEquals(newStatus, BID_STATUS.BID_ACCEPTED);
  }

}
