class BinaryIndexedTree {
    private final int n;
    private final int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;

        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + (nums[i] % 2 == 1 ? a : -b);
        }

        long[] st = s.clone();
        Arrays.sort(st);

        int m = 0;
        for (long x : st) {
            if (m == 0 || st[m - 1] != x) {
                st[m++] = x;
            }
        }

        BinaryIndexedTree bit = new BinaryIndexedTree(m + 1);

        long ans = 0;

        for (long v : s) {
            int x = Arrays.binarySearch(st, 0, m, v) + 1;
            ans += bit.query(x);
            bit.update(x, 1);
        }

        return ans;
    }
}