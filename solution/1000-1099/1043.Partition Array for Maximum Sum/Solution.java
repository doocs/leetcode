class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= Math.max(0, i - k + 1); --j) {
                mx = Math.max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = Math.max(f[i + 1], t);
            }
        }
        return f[n];
    }
}