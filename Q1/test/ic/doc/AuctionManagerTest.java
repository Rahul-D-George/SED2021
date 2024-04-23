package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuctionManagerTest {

  public JUnitRuleMockery context = new JUnitRuleMockery();
  PaymentSystem ps = context.mock(PaymentSystem.class);
  Dispatcher ds = context.mock(Dispatcher.class);

  Bidder alice = context.mock(Bidder.class, "alice");
  Bidder charles = context.mock(Bidder.class, "charles");
  Bidder david = context.mock(Bidder.class, "david");


  Item item = context.mock(Item.class);
  Seller seller = context.mock(Seller.class);
  AuctionManager auctionManager = new AuctionManager(ps, ds, seller, item);


  @Test
  public void AuctionsCanBeInitialisedWithInitialSuccessfulBid() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, alice);
    }});

    BID_STATUS status = auctionManager.receiveBid(10, alice);
    assertEquals(status, BID_STATUS.BID_ACCEPTED);
  }

  @Test
  public void LowBidsAreRejected() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, alice);
    }});

    auctionManager.receiveBid(10, alice);
    BID_STATUS newStatus = auctionManager.receiveBid(5, charles);
    assertEquals(newStatus, BID_STATUS.BID_TOO_LOW);
  }

  @Test
  public void NewHighestBidsReplaceTheOldAndAreSuccessful() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, alice);
      oneOf(ps).charge(20, charles);
    }});

    auctionManager.receiveBid(10, alice);
    BID_STATUS newStatus = auctionManager.receiveBid(20, charles);
    assertEquals(newStatus, BID_STATUS.BID_ACCEPTED);
  }

  @Test
  public void EndingAuctionRefundsBiddersPaysSellerAndDispatchesItem() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, alice);
      oneOf(ps).charge(20, david);
      oneOf(ps).pay(20, seller);
      oneOf(ps).pay(10, alice);
      oneOf(ds).dispatch(item, david);
    }});

    auctionManager.receiveBid(10, alice);
    auctionManager.receiveBid(5, charles);
    auctionManager.receiveBid(20, david);
    auctionManager.endAuction();
  }

}
