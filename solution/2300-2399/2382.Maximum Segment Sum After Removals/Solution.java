class Solution {
    private int[] p;
    private long[] s;

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        p = new int[n];
        s = new long[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        long[] ans = new long[n];
        long mx = 0;
        for (int j = n - 1; j > 0; --j) {
            int i = removeQueries[j];
            s[i] = nums[i];
            if (i > 0 && s[find(i - 1)] > 0) {
                merge(i, i - 1);
            }
            if (i < n - 1 && s[find(i + 1)] > 0) {
                merge(i, i + 1);
            }
            mx = Math.max(mx, s[find(i)]);
            ans[j - 1] = mx;
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        p[pa] = pb;
        s[pb] += s[pa];
    }
}