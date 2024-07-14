class Solution {
    public int[] getAverages(int[] nums, int k) {
        k = k << 1 | 1;
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        if (k > n) {
            return ans;
        }
        long s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i];
        }
        int j = k / 2;
        ans[j] = (int) (s / k);
        for (int i = k; i < n; ++i) {
            s += nums[i] - nums[i - k];
            ans[++j] = (int) (s / k);
        }
        return ans;
    }
}