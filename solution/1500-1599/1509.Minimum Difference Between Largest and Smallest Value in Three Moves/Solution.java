class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n < 5) {
            return 0;
        }
        Arrays.sort(nums);
        long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = Math.min(ans, (long) nums[n - 1 - r] - nums[l]);
        }
        return (int) ans;
    }
}