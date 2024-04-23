package tennis;

import javax.swing.*;

public class TennisScorer {

  private final TennisScorerModel tennisScorerModel = new TennisScorerModel();

  public static void main(String[] args) {
    new TennisScorer().display();
  }

  private void display() {

    JFrame window = new JFrame("Tennis");
    window.setSize(400, 150);

    JButton playerOneScores = new JButton("Player One Scores");
    JButton playerTwoScores = new JButton("Player Two Scores");

    JTextField scoreDisplay = new JTextField(20);
    scoreDisplay.setHorizontalAlignment(JTextField.CENTER);
    scoreDisplay.setEditable(false);

    playerOneScores.addActionListener(
            e -> {
              tennisScorerModel.playerOneWinsPoint();
              scoreDisplay.setText(tennisScorerModel.score());
              if (gameHasEnded()) {
                playerOneScores.setEnabled(false);
                playerTwoScores.setEnabled(false);
              }
            });

    playerTwoScores.addActionListener(
            e -> {
              tennisScorerModel.playerTwoWinsPoint();
              scoreDisplay.setText(tennisScorerModel.score());
              if (gameHasEnded()) {
                playerOneScores.setEnabled(false);
                playerTwoScores.setEnabled(false);
              }
            });

    JPanel panel = new JPanel();
    panel.add(playerOneScores);
    panel.add(playerTwoScores);
    panel.add(scoreDisplay);

    window.add(panel);

    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);

  }

  private boolean gameHasEnded() {
    return tennisScorerModel.score().contains("Game");
  }

}