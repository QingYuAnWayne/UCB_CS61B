public class UnionFind {

    int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parents.length || vertex < 0) {
            throw new IllegalArgumentException("v1 is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -1 * parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) {
            return;
        }
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (sizeOf(v1Root) <= sizeOf(v2Root)) { // v1Root -> v2Root
            parents[v2Root] += parents[v1Root];
            parents[v1Root] = v2Root;
        } else { // v2Root -> v1Root
            parents[v1Root] += parents[v2Root];
            parents[v2Root] = v1Root;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int p = vertex;
        int root;
        while (parent(p) >= 0) {
            p = parent(p);
        }
        root = p;
        p = vertex;
        while (parent(p) >= 0) {
            int tmp = p;
            p = parent(p);
            parents[tmp] = root;
        }
        return p;
    }

}
