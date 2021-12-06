public class OffByN implements CharacterComparator{

    private int N;

    public OffByN(int N) {
        this.N = N;
    }


    /**
     * Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x
     * @param y
     */
    @Override
    public boolean equalChars(char x, char y) {
        if (Character.isLetter(x) && Character.isLetter(y)) {
            return x - y == N || y - x == N;
        } else {
            System.out.println("Error! None-Letter is not accepted");
            return false;
        }
    }

}
