public class OffByOne implements CharacterComparator{
    /**
     * Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x
     * @param y
     */
    @Override
    public boolean equalChars(char x, char y) {
        if (Character.isLetter(x) && Character.isLetter(y)) {
            return x - y == 1 || y - x == 1;
        } else {
            System.out.println("Error! None-Letter is not accepted");
            return false;
        }
    }
}
