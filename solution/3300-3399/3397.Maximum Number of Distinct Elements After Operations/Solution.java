class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0, pre = Integer.MIN_VALUE;
        for (int x : nums) {
            int cur = Math.min(x + k, Math.max(x - k, pre + 1));
            if (cur > pre) {
                ++ans;
                pre = cur;
            }
        }
        return ans;
    }
}
