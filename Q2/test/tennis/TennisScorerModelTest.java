package tennis;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class TennisScorerModelTest {

    @Test
    public void FourConsecutivePointsLeadsToGame() {
        TennisScorerModel ts = new TennisScorerModel();
        for (int i = 0; i < 4; i++) {
            ts.playerOneWinsPoint();
        }
        assertThat(ts.score(), containsString("Game Player 1"));
    }

    @Test
    public void FortyFortyLeadsToDeuceAdvantageThenWin() {
        TennisScorerModel ts = new TennisScorerModel();
        for (int i = 0; i < 3; i++) {
            ts.playerOneWinsPoint();
            ts.playerTwoWinsPoint();
        }
        assertThat(ts.score(), containsString("Deuce"));
        ts.playerOneWinsPoint();
        assertThat(ts.score(), containsString("Advantage Player 1"));
        ts.playerOneWinsPoint();
        assertThat(ts.score(), containsString("Game Player 1"));
    }

    @Test
    public void LossWithAdvantageLeadsToDeuce() {
        TennisScorerModel ts = new TennisScorerModel();
        for (int i = 0; i < 3; i++) {
            ts.playerOneWinsPoint();
            ts.playerTwoWinsPoint();
        }
        assertThat(ts.score(), containsString("Deuce"));
        ts.playerOneWinsPoint();
        assertThat(ts.score(), containsString("Advantage Player 1"));
        ts.playerTwoWinsPoint();
        assertThat(ts.score(), containsString("Deuce"));
    }

}
