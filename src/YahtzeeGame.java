public class YahtzeeGame {
    // instance variables
    int score;
    YahtzeeDice gameDice;
    ScoreCard scores = new ScoreCard();

    // constructor
    public YahtzeeGame() {
        score = 0;
        gameDice = new YahtzeeDice();
    }

    // instance methods
    public void startGame() {
        while (true) {
            if (!scores.gameFinished()) {
                playTurn();
            }
            else {
                break;
            }
        }
        System.out.println("Game Over");
        System.out.println("Your Score Was: " + scores.getScoreTotal());
    }
    public void playTurn() {
        outer:
        for (int i = 0; i < 3; i++) {
            System.out.println("Starting round (" + (3 - i) + " rounds left)");
            gameDice.roll();
            System.out.print("The 5 dice read: ");
            System.out.println(gameDice.toString());
            roundScore();
            while (true) {
                int choice = 0;
                if (i != 2) {
                    System.out.println("Enter a Number to Select Score or Enter 0 to Reroll: ");
                } else {
                    System.out.println("Enter a Number to Select Score: ");
                }
                choice = TextIO.getlnInt();
                if (choice != 0) {
                    if (!scores.getFinished(choice - 1)) {
                        scores.setFinishedScore(choice - 1, gameDice.getDiceValues());
                        scores.setFinished(choice - 1);
                        break outer;
                    } else {
                        System.out.println(choice + " is already chosen");
                        System.out.println("Reselect");
                        continue;
                    }
                }
                break;
            }

            for (int j = 0; j < gameDice.getDice().length; j++) {
                System.out.println("Hold die " + (j + 1) + "? ");
                char c = TextIO.getlnChar();
                if (c == 'y') {
                    gameDice.setHeldDice(j);
                } else {
                    gameDice.setUnheld(j);
                }
            }
            gameDice.roll();
            System.out.println();
        }

    }

    public void roundScore() {
        String scoreString = "***Current Scorecard***\n";
        scoreString += String.format("%-30s", "1) Ones: " + scores.getSingleScore(1, gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "2) Twos: " + scores.getSingleScore(2, gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "3) Threes: " + scores.getSingleScore(3, gameDice.getDiceValues()));
        scoreString += "\n";
        scoreString += String.format("%-30s", "4) Fours: " + scores.getSingleScore(4, gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "5) Fives: " + scores.getSingleScore(5, gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "6) Sixes: " + scores.getSingleScore(6, gameDice.getDiceValues()));
        scoreString += "\n";
        scoreString += String.format("%-30s", "7) Three of a Kind: " + scores.getThreeOfAKind(gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "8) Four of a Kind: " + scores.getFourOfAKind(gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "9) Full House: " + + scores.getFullHouse(gameDice.getDiceValues()));
        scoreString += "\n";
        scoreString += String.format("%-30s", "10) Small Straight: " + + scores.getSmallStraight(gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "11) Large Straight: " + scores.getLargeStraight(gameDice.getDiceValues()));
        scoreString += String.format("%-30s", "12) Chance: " + + scores.getChance(gameDice.getDiceValues()));
        scoreString += "\n";
        scoreString += String.format("%-30s", "13) Yahtzee: " + + scores.getYahtzeeScore(gameDice.getDiceValues()));
        System.out.println(scoreString);
    }

}
