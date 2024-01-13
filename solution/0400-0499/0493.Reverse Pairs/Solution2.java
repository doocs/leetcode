class Solution {
    public int reversePairs(int[] nums) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int num : nums) {
            ts.add((long) num);
            ts.add((long) num * 2);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 0;
        for (long num : ts) {
            m.put(num, ++idx);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get((long) nums[i]);
            ans += tree.query(x - 1);
            tree.update(m.get((long) nums[i] * 2), 1);
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