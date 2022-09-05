class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree.update(x, 1);
            boolean case1 = x - k - 1 > 0 && tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                && tree.query(x - 1) - tree.query(x - k - 1) == 0;
            boolean case2 = x + k + 1 <= n && tree.query(x + k + 1) - tree.query(x + k) == 1
                && tree.query(x + k) - tree.query(x) == 0;
            if (case1 || case2) {
                return i + 1;
            }
        }
        return -1;
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