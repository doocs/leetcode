class BinaryIndexedTree {
    private final int n;
    private final long[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    void update(int x, long delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    long query(int x) {
        long s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public long distantSubarrays(int[] nums, int goal, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];

        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        long[] st = s.clone();
        Arrays.sort(st);

        long ans = (long) n * (n + 1) / 2;
        BinaryIndexedTree bit = new BinaryIndexedTree(st.length + 1);

        for (long v : s) {
            long a = v - goal - k + 1L;
            long b = v - goal + k - 1L;

            int l = lowerBound(st, a) + 1;
            int r = lowerBound(st, b + 1);

            if (l <= r) {
                ans -= bit.query(r) - bit.query(l - 1);
            }

            bit.update(lowerBound(st, v) + 1, 1);
        }

        return ans;
    }

    private int lowerBound(long[] nums, long target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int m = (l + r) >>> 1;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }
}