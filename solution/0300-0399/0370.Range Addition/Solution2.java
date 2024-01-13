class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        BinaryIndexedTree tree = new BinaryIndexedTree(length);
        for (int[] e : updates) {
            int start = e[0], end = e[1], inc = e[2];
            tree.update(start + 1, inc);
            tree.update(end + 2, -inc);
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = tree.query(i + 1);
        }
        return ans;
    }
}

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