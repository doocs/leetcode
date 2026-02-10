class Solution {
    public int[] delayedCount(int[] nums, int k) {
        int n = nums.length;
        var cnt = new HashMap<Integer, Integer>();
        int[] ans = new int[n];
        for (int i = n - k - 2; i >= 0; --i) {
            cnt.merge(nums[i + k + 1], 1, Integer::sum);
            ans[i] = cnt.getOrDefault(nums[i], 0);
        }
        return ans;
    }
}
