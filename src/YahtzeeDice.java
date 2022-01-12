public class YahtzeeDice {
    private Die[] dice = new Die [5];
    private Die[] heldDice = new Die [5];

    // --- Constructors ---
    public YahtzeeDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

//    // overloaded constructor to allow for different # of sides
//    public YahtzeeDice(int numSides) {
//        for (int i = 0; i < dice.length; i++) {
//            dice[i] = new Die(numSides);
//        }
//    }

    public void roll() {
        for (int i = 0; i < dice.length; i++) {
            if (diceIsHeld(i) == false) {
                dice[i].roll();
            }
        }
    }

    // return the array of Die objects
    public Die[] getDice() {
        return dice;
    }

    // If the format above isn't helpful
    // this method will return the int values of each Die object
    public int[] getDiceValues() {
        return new int[] {dice[0].getCurrentValue(),
                dice[1].getCurrentValue(),
                dice[2].getCurrentValue(),
                dice[3].getCurrentValue(),
                dice[4].getCurrentValue() };
    }

    // String representation of Yahtzee Dice
    // Again, don't reinvent wheel
    public String toString() {
        return (dice[0].toString() + " "
                + dice[1].toString() + " "
                + dice[2].toString() + " "
                + dice[3].toString() + " "
                + dice[4].toString() + " ");
    }

    public void setHeldDice(int position) {
        heldDice[position] = dice[position];
    }
    public void setUnheld(int position) {
        heldDice[position] = null;
    }
    public boolean diceIsHeld(int position) {
        return heldDice[position] != null;
    }
}
