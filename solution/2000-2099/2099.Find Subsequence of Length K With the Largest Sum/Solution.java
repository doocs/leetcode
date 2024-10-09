class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> nums[i] - nums[j]);
        Arrays.sort(idx, n - k, n);
        int[] ans = new int[k];
        for (int i = n - k; i < n; ++i) {
            ans[i - (n - k)] = nums[idx[i]];
        }
        return ans;
    }
}