import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("Noon"));

        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("cabd", offByOne));
        assertTrue(palindrome.isPalindrome("",offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("car", offByOne));
        assertFalse(palindrome.isPalindrome("card", offByOne));
        assertFalse(palindrome.isPalindrome("stupid", offByOne));

        OffByN offBy1 = new OffByN(1);
        assertTrue(palindrome.isPalindrome("flake", offBy1));
        assertTrue(palindrome.isPalindrome("cabd", offBy1));
        assertTrue(palindrome.isPalindrome("",offBy1));
        assertTrue(palindrome.isPalindrome("a", offBy1));
        assertFalse(palindrome.isPalindrome("car", offBy1));
        assertFalse(palindrome.isPalindrome("card", offBy1));
        assertFalse(palindrome.isPalindrome("stupid", offBy1));
    }
}