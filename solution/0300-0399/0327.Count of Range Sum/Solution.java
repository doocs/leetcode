class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        TreeSet<Long> ts = new TreeSet<>();
        for (long s : preSum) {
            ts.add(s);
            ts.add(s - upper);
            ts.add(s - lower);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 1;
        for (long s : ts) {
            m.put(s, idx++);
        }
        int ans = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        for (long s : preSum) {
            int i = m.get(s - upper);
            int j = m.get(s - lower);
            ans += tree.query(j) - tree.query(i - 1);
            tree.update(m.get(s), 1);
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