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
    public int getPermutationIndex(int[] perm) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int n = perm.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n + 1);
        long[] f = new long[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] * i % mod;
        }
        for (int i = 0; i < n; ++i) {
            int cnt = perm[i] - 1 - tree.query(perm[i]);
            ans = (ans + cnt * f[n - i - 1] % mod) % mod;
            tree.update(perm[i], 1);
        }
        return (int) ans;
    }
}