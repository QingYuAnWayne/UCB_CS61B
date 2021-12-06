import org.junit.Assert;
import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> arr1= new ArrayDeque<>();

        arr1.addFirst(5);
        arr1.addFirst(3);
        arr1.addFirst(2);
        // [5, 0, 0, 0, 0, 0, 2, 3]
        String actual1 = arr1.printArray();
        String expected1 = "[5, 0, 0, 0, 0, 0, 2, 3]";

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void addLastTest() {
        ArrayDeque<Integer> arr1= new ArrayDeque<>();

        arr1.addFirst(5);
        arr1.addFirst(3);
        arr1.addFirst(2);
        arr1.addLast(1);
        arr1.addLast(4);
        arr1.addLast(7);
        arr1.addLast(2);
        // [5, 1, 4, 7, 2, 0, 2, 3]
        arr1.addLast(5);
        // [5, 1, 4, 7, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3]
        String actual1 = arr1.printArray();
        String expected1 = "[5, 1, 4, 7, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3]";

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void printDequeTest() {
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addFirst(3);
        arr1.addFirst(2);
        arr1.addLast(1);
        arr1.addLast(4);
        arr1.addLast(7);
        arr1.addLast(2);
        // [5, 1, 4, 7, 2, 0, 2, 3]
        String expected1 = "2 3 5 1 4 7 2 ";
        String actual1 = arr1.printDeque();

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addLast(3);
        arr1.addLast(2);
        arr1.addLast(1);
        Integer actual1 = arr1.removeFirst();
        Integer expected1 = 5;
        Assert.assertEquals(expected1, actual1);
        String actual2 = arr1.printArray();
        String expected2 = "[0, 3, 2, 1, 0, 0, 0, 0]";
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void removeFirstTest2() {
        //[5, 1, 0, 0, 0, 0, 2, 3]
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addLast(1);
        arr1.addFirst(3);
        arr1.addFirst(2);
        Integer actual1 = arr1.removeFirst();
        Integer expected1 = 2;
        Assert.assertEquals(expected1, actual1);
        String actual2 = arr1.printArray();
        String expected2 = "[5, 1, 0, 0, 0, 0, 0, 3]";
        Assert.assertEquals(expected2, actual2);

    }

    @Test
    public void removeLastTest() {
        // [5, 3, 2, 1, 0, 0, 0, 0] = removeLast() =>  [5, 3, 2, 0, 0, 0, 0, 0]
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addLast(3);
        arr1.addLast(2);
        arr1.addLast(1);
        Integer actual1 = arr1.removeLast();
        Integer expected1 = 1;
        Assert.assertEquals(expected1, actual1);
        String actual2 = arr1.printArray();
        String expected2 = "[5, 3, 2, 0, 0, 0, 0, 0]";
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void removeLastTest2() {
        //[5, 1, 0, 0, 0, 0, 2, 3]
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addLast(1);
        arr1.addFirst(3);
        arr1.addFirst(2);
        Integer actual1 = arr1.removeLast();
        Integer expected1 = 1;
        Assert.assertEquals(expected1, actual1);
        String actual2 = arr1.printArray();
        String expected2 = "[5, 0, 0, 0, 0, 0, 2, 3]";
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void ArrayDequeTest() {
        ArrayDeque<Integer> arr1= new ArrayDeque<>();
        arr1.addFirst(5);
        arr1.addLast(3);
        arr1.addLast(2);
        arr1.addLast(1);
        ArrayDeque<Integer> arr2 = new ArrayDeque<>(arr1);
        String expected1 = arr1.printDeque();
        String actual1 = arr2.printDeque();
        Assert.assertEquals(expected1, actual1);
    }
}
