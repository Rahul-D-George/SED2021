package tennis;

import javax.swing.*;

public class TennisScorer implements Observer {

  private final TennisScorerModel tennisScorerModel;
  private JTextField scoreDisplay;
  JButton playerOneScores;
  JButton playerTwoScores;

  public TennisScorer(TennisScorerModel model) {
      this.tennisScorerModel = model;
      this.tennisScorerModel.addObserver(this);
  }

  public static void main(String[] args) {
    new TennisScorer(new TennisScorerModel()).display();
  }

  private void display() {

    JFrame window = new JFrame("Tennis");
    window.setSize(400, 150);

    playerOneScores = new JButton("Player One Scores");
    playerTwoScores = new JButton("Player Two Scores");

    scoreDisplay = new JTextField(20);
    scoreDisplay.setHorizontalAlignment(JTextField.CENTER);
    scoreDisplay.setEditable(false);

    playerOneScores.addActionListener(
            e -> {
              tennisScorerModel.playerOneWinsPoint();
            });

    playerTwoScores.addActionListener(
            e -> {
              tennisScorerModel.playerTwoWinsPoint();
            });

    JPanel panel = new JPanel();
    panel.add(playerOneScores);
    panel.add(playerTwoScores);
    panel.add(scoreDisplay);

    window.add(panel);

    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);

  }

  public void update() {
      scoreDisplay.setText(tennisScorerModel.score());
      if (gameHasEnded()) {
          playerOneScores.setEnabled(false);
          playerTwoScores.setEnabled(false);
      }
  }

  private boolean gameHasEnded() {
    return tennisScorerModel.score().contains("Game");
  }

}