import static org.junit.Assert.*;
import org.junit.Test;


public class testUnionFind {

    @Test
    public void testSizeOf() {
        UnionFind u = new UnionFind(8);
        int expected = 1;
        int actual = u.sizeOf(0);
        assertEquals(expected, actual);

        u.union(0, 1);
        expected = 2;
        actual = u.sizeOf(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testParent() {
        UnionFind u = new UnionFind(8);
        int expected = -1;
        int actual = u.parent(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testConnected() {
        UnionFind u = new UnionFind(8);
        u.union(0, 1);
        assertTrue(u.connected(0, 1));

        u.union(2, 3);
        u.union(0, 3);
        assertTrue(u.connected(0, 3));
        assertTrue(u.connected(1, 2));
        assertEquals(-4, u.parent(3));

        u.union(4, 5);
        u.union(4, 6);
        u.union(4, 0);
        assertEquals(-7, u.parent(3));
    }
}
