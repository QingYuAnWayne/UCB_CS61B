/** This is a Linked List Deque class.
 *
 * @author Qing YuAn
 * @since 27 Nov 2021
 */
public class LinkedListDeque<T> implements Deque<T> {
    /** The sentinel node in Deque. */
    private IntNode sentinel;
    /** The size of Deque. */
    private int size;

    /** IntNode class, which includes two pointers. */
    private class IntNode {
        /** The item of the IntNode. */
        private T item;
        /** The previous IntNode of this IntNode. */
        private IntNode prev;
        /** The next IntNode of this IntNode. */
        private IntNode next;

        /** Creates an IntNode.
         * @param i The item of the IntNode
         * @param p The previous IntNode of this IntNode
         * @param n The next IntNode of this IntNode
         * */
        IntNode(T i, IntNode p, IntNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Creates a deep copy of other.
     * @param other The deque to be copied
     * */
    public LinkedListDeque(LinkedListDeque<T> other) {
        size = 0;
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        for (int i = 0; i < other.size; i++) {
            this.addLast(other.get(i));
        }
    }

    /** Adds an item of type T to the front of the deque.
     * @param item The item to be added to the front of the deque
     * */
    @Override
    public void addFirst(T item) {
        size += 1;
        IntNode node = new IntNode(item, sentinel, sentinel.next);
        node.next.prev = node;
        sentinel.next = node;
    }

    /** Adds an item of type T to the back of the deque.
     * @param item The item to be added to the back of the deque
     * */
    @Override
    public void addLast(T item) {
        size += 1;
        IntNode node = new IntNode(item, sentinel.prev, sentinel);
        node.prev.next = node;
        sentinel.prev = node;
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
        for (int i = 0; i < size; i++) {
            System.out.print(get(i).toString() + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    @Override
    public  T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            IntNode first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            return first.item;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            IntNode last = sentinel.prev;
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            return last.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth.
     *  If no such item exists, returns null.
     *  @param index The given index
     *  @return The item at the given index
     *  */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            IntNode p = sentinel;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    /** This is a Helper Method for getRecursive().
     * @param p The IntNode of the beginning
     * @param index The index of the IntNode to get
     * @return The IntNode to get in index
     * */
    private IntNode get(IntNode p, int index) {
        if (index == 0) {
            return p;
        } else {
            return get(p.next, index - 1);
        }
    }

    /** Same as get, but uses recursion.
     * @param index The index of the item to get
     * @return The item in index
     * */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            IntNode p = sentinel.next;
            return get(p, index).item;
        }
    }

}
