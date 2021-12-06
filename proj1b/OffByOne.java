public class OffByOne implements CharacterComparator{
    /**
     * Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x
     * @param y
     */
    @Override
    public boolean equalChars(char x, char y) {
            return x - y == 1 || y - x == 1;
    }

}
