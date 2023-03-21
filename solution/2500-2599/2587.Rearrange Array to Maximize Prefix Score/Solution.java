class Solution {
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[n - i - 1];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
}