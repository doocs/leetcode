class Solution {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int n = a.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = a[0];
        dp2[n - 1] = a[n - 1];
        for (int i = 1; i < n; ++i) {
            dp1[i] = dp1[i - 1] * a[i];
        }
        for (int i = n - 2; i >= 0; --i) {
            dp2[i] = dp2[i + 1] * a[i];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = (i - 1 < 0 ? 1 : dp1[i - 1]) * (i + 1 >= n ? 1 : dp2[i + 1]);
        }
        return res;
    }
}