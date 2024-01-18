class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}

class Solution {
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(m + n);
        int[] pos = new int[m + 1];
        for (int i = 1; i <= m; ++i) {
            pos[i] = n + i;
            tree.update(n + i, 1);
        }
        int[] ans = new int[n];
        int k = 0;
        for (int i = 0; i < n; ++i) {
            int v = queries[i];
            int j = pos[v];
            tree.update(j, -1);
            ans[k++] = tree.query(j);
            pos[v] = n - i;
            tree.update(n - i, 1);
        }
        return ans;
    }
}