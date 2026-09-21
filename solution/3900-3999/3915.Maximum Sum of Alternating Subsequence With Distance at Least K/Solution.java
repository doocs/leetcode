class BinaryIndexedTree {
    private final int n;
    private final long[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    void update(int x, long val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += x & -x;
        }
    }

    long query(int x) {
        long ans = 0;
        while (x > 0) {
            ans = Math.max(ans, c[x]);
            x -= x & -x;
        }
        return ans;
    }
}

class Solution {
    public long maxAlternatingSum(int[] nums, int k) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        BinaryIndexedTree bit0 = new BinaryIndexedTree(m);
        BinaryIndexedTree bit1 = new BinaryIndexedTree(m);
        int n = nums.length;
        long[][] f = new long[n][2];
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k) {
                int r = rank(sorted, m, nums[i - k]);
                bit0.update(r, f[i - k][0]);
                bit1.update(m + 1 - r, f[i - k][1]);
            }
            int r = rank(sorted, m, nums[i]);
            f[i][0] = nums[i] + bit1.query(m - r);
            f[i][1] = nums[i] + bit0.query(r - 1);
            ans = Math.max(ans, Math.max(f[i][0], f[i][1]));
        }
        return ans;
    }

    private int rank(int[] sorted, int m, int x) {
        int l = 0, r = m;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (sorted[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
