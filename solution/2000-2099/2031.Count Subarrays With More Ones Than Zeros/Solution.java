class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        n += (int) 1e5 + 1;
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        x += (int) 1e5 + 1;
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        x += (int) 1e5 + 1;
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        x += (int) 1e5 + 1;
        return x & -x;
    }
}

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (nums[i] == 1 ? 1 : -1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(n + 1);
        int ans = 0;
        for (int v : s) {
            ans = (ans + tree.query(v - 1)) % MOD;
            tree.update(v, 1);
        }
        return ans;
    }
}