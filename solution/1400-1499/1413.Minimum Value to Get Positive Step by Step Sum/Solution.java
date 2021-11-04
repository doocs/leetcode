class Solution {
    public int minStartValue(int[] nums) {
        int n = nums.length;
        int perSum = 0;
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            perSum += num;
            ans = Math.min(ans, perSum);
        }
        return ans < 1 ? 1 - ans : 1;
    }
}