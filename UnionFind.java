public class UnionFind {
    private int size; // number of elements
    private int[] sz; // to track the size of each component/set/group. info generally stored at a root node.
    private int[] id; // id[i] points to the parent of i, tells which group it belongs to. if id[i] == i, i is a root node.
    private int numComponents; // to track the number of groups/components


    public UnionFind(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }

        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // path compression for "amortized constant time, α(n) or O(n)+"
        // having every node on the path point to the root node
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q); // since its calling the find() method, path compression will also be called
    }

    public int componentSize(int p) {
        return sz[find(p)]; // size info stored at root
    }

    public int components() {
        return numComponents;
    } 
    
    public int size() {
        return size;
    }

    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if (root1 == root2) return;

        if (sz[root1] >= sz[root2]) {
            sz[root1] += sz[root2];
            id[root2] = id[root1]; 
        } else {
            sz[root2] += sz[root1];
            id[root1] = id[root2];
        }

        numComponents--; // two groups merging into one, so there's now one less group.
    }
}