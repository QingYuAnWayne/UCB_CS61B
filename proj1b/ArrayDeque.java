/** A Deque based on Array.
 * @author Qing YuAn
 * @since 30 Nov 2021
 * */
public class ArrayDeque<T> implements Deque<T>{
    public static final int DEFAULT_SIZE = 8;
    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create an empty Deque. */
    public ArrayDeque() {
        arr = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a deep copy of other.
     * @param other The deque to be copied
     * */
    public ArrayDeque(ArrayDeque<T> other) {
        arr = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = 1;

        for (int i = 0; i < other.size; i++) {
            addLast(other.get(i));
        }
    }


    /** Resize the array to bigger or smaller.
     * i.e:
     *        nextFirst & nextLast                   nextLast                 nextFirst
     *                 |                                |                       |
     * [5, 1, 4, 7, 2, 0, 2, 3]     =>  [5, 1, 4, 7, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3]
     *
     *     nextLast                            nextFirst                       nextLast    nextFirst
     *        |                                   |                               |           |
     * [5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3] = removeFirst() => [5, 1, 0, 0, 0, 0, 0, 3]
     *
     *                              nextFirst     nextLast                      nextLast  nextFirst
     *                                |              |                              |        |
     * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, a, d, e, n, 0] = resize(8) => [a, d, e, n, 0, 0, 0, 0]
     * @param newSize The new size of array.
     * */
    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        int sizeChange = newSize - arr.length;
        if (nextFirst < nextLast) {
            System.arraycopy(arr, nextFirst + 1, newArr, 0, size);
            nextLast = size;
            nextFirst = newArr.length - 1;
            arr = newArr;
        } else {
            System.arraycopy(arr, 0, newArr, 0, nextLast);
            System.arraycopy(arr, checkIndex(nextFirst + 1), newArr, nextFirst + sizeChange + 1, arr.length - nextFirst - 1);
            arr = newArr;
            nextFirst = nextLast + newSize - size - 1;
        }
    }

    /** Print the items.
     * @return The String of the items.
     * */
    public String printArray() {
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                res.append("0");
            } else {
                res.append(arr[i].toString());
            }
            res.append(", ");
        }
        res.deleteCharAt(res.length() - 1);
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    /**  Adds an item of type T to the front of the deque.
     *
     * i.e,
     *                    nextFirst                  nextFirst
     *                       |                          |
     * [5, 3, 2, 0, 0, 0, 0, 0] = addFirst(1) =>  [5, 3, 2, 0, 0, 0, 0, 1]
     *
     * @param item an item of type T to be added
     * */
    @Override
    public void addFirst(T item) {
        // If the array is going to be full
        if (nextFirst == nextLast) {
            resize(arr.length * 2);
        }

        size += 1;
        arr[nextFirst] = item;
        nextFirst = checkIndex(nextFirst - 1);
    }

    /**  Adds an item of type T to the back of the deque.
     *
     * i.e,
     *        nextLast                                  nextLast
     *           |                                         |
     * [5, 3, 2, 0, 0, 0, 0, 0] = addLast(1) =>  [5, 3, 2, 1, 0, 0, 0, 0]
     *
     * @param item an item of type T to be added
     * */
    @Override
    public void addLast(T item) {
        // If the array is going to be full
        if (nextFirst == nextLast) {
            resize(arr.length * 2);
        }

        size += 1;
        arr[nextLast] = item;
        nextLast = checkIndex(nextLast + 1);

    }

    /** Add index helper.
     * i.e:
     *  the length of the array is 5, checkIndex(4++) -> 0
     *  the length of the array is 5, checkIndex(0--) -> 4
     * @param index the index to be changed
     * @return the changed index
     * */
    private int checkIndex(int index) {
        if (index >= arr.length) {
            index %= arr.length;
        } else if (index < 0) {
            index += arr.length;
        }
        return index;
    }


    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            System.out.print(get(i).toString() + " ");
            res.append(get(i).toString() + " ");
        }
        System.out.println();
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists, returns null.
     *  @param index the index of the item to be got
     *  @return the item at the given index
     *  */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return arr[checkIndex(nextFirst + 1 + index)];
        }
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     *  i.e,
     *                    nextFirst               nextFirst
     *                       |                       |
     * [5, 3, 2, 1, 0, 0, 0, 0] = removeFirst() =>  [0, 3, 2, 1, 0, 0, 0, 0]
     *     nextLast                         nextFirst                          nextLast    nextFirst
     *        |                                |                                  |           |
     * [5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3] = removeFirst() => [5, 1, 0, 0, 0, 0, 0, 3]
     * */
    @Override
    public T removeFirst() {
        if ((size - 1) / (float) arr.length < 0.25 && arr.length > DEFAULT_SIZE) {
            resize(arr.length / 2);
        }

        nextFirst = checkIndex(nextFirst + 1);
        size -= 1;
        T res = arr[nextFirst];
        arr[nextFirst] = null;
        return res;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     *  i.e,
     *            nextLast                                nextLast
     *               |                                       |
     * [5, 3, 2, 1, 0, 0, 0, 0] = removeLast() =>  [5, 3, 2, 0, 0, 0, 0, 0]
     *     nextLast                         nextFirst                      nextLast    nextFirst
     *        |                                |                              |           |
     * [5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3] = removeLast() => [5, 0, 0, 0, 0, 0, 2, 3]
     * */
    @Override
    public T removeLast() {
        if ((size - 1) / (float) arr.length < 0.25 && arr.length > DEFAULT_SIZE) {
            resize(arr.length / 2);
        }
        nextLast = checkIndex(nextLast - 1);
        size -= 1;
        T res = arr[nextLast];
        arr[nextLast] = null;
        return res;
    }
}
