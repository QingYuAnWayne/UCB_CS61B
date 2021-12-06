/**
 * @author Qing YuAn
 * @since 4 Dec 2021
 * */
public class Palindrome {

    /** Given a String, wordToDeque should return a Deque where the characters
     *  appear in the same order as in the String.
     *  For example,
     *  wordToDeque("persiflage") -> [p, e, r, s, i, f, l, a, g, e]
     *  @param word the given String
     *  @return a Deque
     *  */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    /** Given a String, judge if the word is a palindrome.
     * @param word the given word
     * @return true if the given word is a palindrome, and false otherwise
     * */
    public boolean isPalindrome(String word) {
        Deque<Character> s = wordToDeque(word);
        return isPalindromeHelper(s);
    }

    /** The isPalindrome recursion helper.
     * @param deque the given word
     * @return true if the given word is a palindrome, and false otherwise
     * */
    private boolean isPalindromeHelper(Deque deque) {
        if (deque.size() == 1 || deque.isEmpty()) {
            return true;
        } else {
            return (deque.removeFirst() == deque.removeLast())
                    && isPalindromeHelper(deque);
        }
    }

    /** Return true if the word is a palindrome according to the character
     * comparison test provided by the CharacterComparator passed in as
     * argument cc.
     * @param word the word to be analysis
     * @param cc an argument
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> s = wordToDeque(word);
        return isPalindromeHelper(s, cc);
    }

    /** The isPalindrome recursion helper.
     * @param deque the given word
     * @param cc the character comparator
     * @return true if the given word is a palindrome, and false otherwise
     * */
    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 1 || deque.isEmpty()) {
            return true;
        } else {
            return cc.equalChars(deque.removeFirst(), deque.removeLast())
                    && isPalindromeHelper(deque, cc);
        }
    }
}
