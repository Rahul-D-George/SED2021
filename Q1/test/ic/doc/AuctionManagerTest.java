package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuctionManagerTest {

  public JUnitRuleMockery context = new JUnitRuleMockery();
  PaymentSystem ps = context.mock(PaymentSystem.class);
  Bidder alice = context.mock(Bidder.class);


  @Test
  public void SuccessfulBidsChargePeople() {
    context.checking(new Expectations(){{
      oneOf(ps).charge(10, alice);
    }});

    AuctionManager auctionManager = new AuctionManager(ps);
    BID_STATUS status = auctionManager.receiveBid(10, alice);
    assertEquals(status, BID_STATUS.BID_ACCEPTED);
  }

}
