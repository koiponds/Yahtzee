import java.util.Arrays;
public class ScoreCard {
    private boolean yahtzeeBonus = false;
    private boolean[] finishedCards = {false, false, false, false, false, false, false, false, false, false, false, false, false};
    private int[] finishedScores = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public boolean gameFinished() {
        for (boolean card : finishedCards) {
            if (!card) {
                return false;
            }
        }
        return true;
    }

    public boolean threeOfAKindChecker(int[] nums) {
        for (int i = 0; i <= 6; i++) {
            if (getTimesShowUp(i, nums) >= 3) {
                return true;
            }
        }
        return false;
    }
    public boolean fourOfAKindChecker(int[] nums) {
        for (int i = 0; i <= 6; i++) {
            if (getTimesShowUp(i, nums) >= 4) {
                return true;
            }
        }
        return false;
    }
    public boolean fullHouseChecker(int[] nums) {
        if (threeOfAKindChecker(nums) == true) {
           for (int i = 1; i < 6; i++) {
               if (getTimesShowUp(i, nums) == 2) {
                   return true;
               }
           }
        }
        return false;
    }
    public boolean smallStraightChecker(int[] nums) {
        for (int i = 0; i <= 2; i++) {
            if (getTimesShowUp(1 + i, nums) > 0 &&
                    getTimesShowUp(2 + i, nums) > 0 &&
                    getTimesShowUp(3 + i, nums) > 0 &&
                    getTimesShowUp(4 + i, nums) > 0) {
                return true;
            }
        }
        return false;
    }
    public boolean largeStraightChecker(int[] nums) {
        for (int i = 0; i <= 1; i++) {
            if (getTimesShowUp(1 + i, nums) > 0 &&
                    getTimesShowUp(2 + i, nums) > 0 &&
                    getTimesShowUp(3 + i, nums) > 0 &&
                    getTimesShowUp(4 + i, nums) > 0 &&
                    getTimesShowUp(5 + i, nums) > 0){
                return true;
            }
        }
        return false;
    }
    public boolean yahtzeeChecker(int[] nums) {
        for (int i = 0; i <= 6; i++) {
            if (getTimesShowUp(i, nums) == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean getFinishedCard(int num) {
        return finishedCards[num];
    }
    public int getTimesShowUp(int num, int[] nums) {
        int timesShowUp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                timesShowUp++;
            }
        }
        return timesShowUp;
    }

    public int addEverythingScore(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total;
    }

    public int getSingleScore(int n, int[] nums) {
        if (finishedCards[n - 1]) {
            return finishedScores[n - 1];
        }
        return getTimesShowUp(n, nums) * n;
    }
    public int getThreeOfAKind(int[] nums) {
        if (finishedCards[6]) {
            return finishedScores[6];
        }
        if (threeOfAKindChecker(nums)) {
            return addEverythingScore(nums);
        }
        return 0;
    }
    public int getFourOfAKind(int[] nums) {
        if (finishedCards[7]) {
            return finishedScores[7];
        }
        if (fourOfAKindChecker(nums)) {
            return addEverythingScore(nums);
        }
        return 0;
    }
    public int getFullHouse(int[] nums) {
        if (finishedCards[8]) {
            return finishedScores[8];
        }
        if (fullHouseChecker(nums)) {
            return 25;
        }
        return 0;
    }
    public int getSmallStraight(int[] nums) {
        if (finishedCards[9]) {
            return finishedScores[9];
        }
        if (smallStraightChecker(nums)) {
            return 30;
        }
        return 0;
    }
    public int getLargeStraight(int[] nums) {
        if (finishedCards[10]) {
            return finishedScores[10];
        }
        if (largeStraightChecker(nums)) {
            return 40;
        }
        return 0;
    }
    public int getChance(int[] nums) {
        if (finishedCards[11]) {
            return finishedScores[11];
        }
        return addEverythingScore(nums);
    }
    public int getYahtzeeScore(int[] nums) {
        if (yahtzeeChecker(nums)) {
            if (yahtzeeBonus == true) {
                return 100;
            }
            yahtzeeBonus = true;
            return 50;
        }
        return 0;
    }

    public void setFinishedScore(int n, int[] nums) {
        if (n == 0) {
            finishedScores[n] = getSingleScore(1, nums);
        }
        if (n == 1) {
            finishedScores[n] = getSingleScore(2, nums);
        }
        if (n == 2) {
            finishedScores[n] = getSingleScore(3, nums);
        }
        if (n == 3) {
            finishedScores[n] = getSingleScore(4, nums);
        }
        if (n == 4) {
            finishedScores[n] = getSingleScore(5, nums);
        }
        if (n == 5) {
            finishedScores[n] = getSingleScore(6, nums);
        }
        if (n == 6) {
            finishedScores[n] = getThreeOfAKind(nums);
        }
        if (n == 7) {
            finishedScores[n] = getFourOfAKind(nums);
        }
        if (n == 8) {
            finishedScores[n] = getFullHouse(nums);
        }
        if (n == 9) {
            finishedScores[n] = getSmallStraight(nums);
        }
        if (n == 10) {
            finishedScores[n] = getLargeStraight(nums);
        }
        if (n == 11) {
            finishedScores[n] = getChance(nums);
        }
        if (n == 12) {
            finishedScores[n] += getYahtzeeScore(nums);
        }
    }

    public void setFinished(int n) {
        finishedCards[n] = true;
    }

    public boolean getFinished(int n) {
        return finishedCards[n];
    }

    public int getScoreTotal() {
        int scoreTotal = 0;
        for (int i = 0; i < finishedScores.length; i++) {
            scoreTotal += finishedScores[i];
        }
        return scoreTotal;
    }
}
