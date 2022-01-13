class Solution {
    public int dominantIndex(int[] nums) {
        int mx = Integer.MIN_VALUE, mid = Integer.MIN_VALUE;
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > mx) {
                mid = mx;
                mx = nums[i];
                ans = i;
            } else if (nums[i] > mid) {
                mid = nums[i];
            }
        }
        return mx >= mid * 2 ? ans : -1;
    }
}