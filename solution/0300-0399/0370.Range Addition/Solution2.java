class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    public int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
}

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        var tree = new BinaryIndexedTree(length);
        for (var e : updates) {
            int l = e[0], r = e[1], c = e[2];
            tree.update(l + 1, c);
            tree.update(r + 2, -c);
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = tree.query(i + 1);
        }
        return ans;
    }
}
