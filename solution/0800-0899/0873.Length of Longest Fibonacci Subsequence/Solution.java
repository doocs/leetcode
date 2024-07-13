class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.put(arr[i], i);
            for (int j = 0; j < i; ++j) {
                f[i][j] = 2;
            }
        }
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = 1; j < i; ++j) {
                int t = arr[i] - arr[j];
                Integer k = d.get(t);
                if (k != null && k < j) {
                    f[i][j] = Math.max(f[i][j], f[j][k] + 1);
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
}