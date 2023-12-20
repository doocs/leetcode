class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] += v;
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
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int n = nums.length;
        int base = n + 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(n + base);
        tree.update(base, 1);
        final int mod = (int) 1e9 + 7;
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x == 0 ? -1 : 1;
            ans += tree.query(s - 1 + base);
            ans %= mod;
            tree.update(s + base, 1);
        }
        return ans;
    }
}