class Solution {
    public int createSortedArray(int[] instructions) {
        int n = 100010;
        int mod = (int) 1e9 + 7;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int ans = 0;
        for (int num : instructions) {
            int a = tree.query(num - 1);
            int b = tree.query(n) - tree.query(num);
            ans += Math.min(a, b);
            ans %= mod;
            tree.update(num, 1);
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