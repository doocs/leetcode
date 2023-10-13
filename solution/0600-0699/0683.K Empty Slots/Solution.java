class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree.update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) == 0) {
                return i;
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