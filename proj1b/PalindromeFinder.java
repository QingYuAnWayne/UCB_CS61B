/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    /** For what N are there the most palindromes in English?
     *  What is the longest offByN palindrome for any N? */
    public String longestWord;

    public PalindromeFinder() {
        longestWord = "";
    }

    public Deque<String> getPalindromes(int N) {
        In in = new In("words.txt");
        Palindrome palindrome = new Palindrome();
        Deque<String> deque = new ArrayDeque<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            OffByN offByN = new OffByN(N);
            if (palindrome.isPalindrome(word, offByN)) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
                deque.addFirst(word);
            }
        }
        return deque;
    }
    public static void main(String[] args) {
        PalindromeFinder palindromeFinder = new PalindromeFinder();

        for (int i = 0; i < 'z' - 'a'; i++) {
            Deque<String> deque = palindromeFinder.getPalindromes(i);
            System.out.println("N = " + i + ", there are " + deque.size() + " palindromes.");
        }
        System.out.println("The longest palindrome is " + palindromeFinder.longestWord + '.');
    }
}