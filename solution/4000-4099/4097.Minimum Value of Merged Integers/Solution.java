class Solution {
    public int minMergedInteger(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i += 2) {
            int v = Integer.parseInt(String.valueOf(nums[i]) + nums[i + 1]);
            ans = Math.min(ans, v);
        }
        return ans;
    }
}