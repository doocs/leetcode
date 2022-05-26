class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] arr = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                arr[idx++] = s;
            }
        }
        Arrays.sort(arr);
        int ans = 0;
        for (int i = left - 1; i < right; ++i) {
            ans = (ans + arr[i]) % MOD;
        }
        return ans;
    }
}