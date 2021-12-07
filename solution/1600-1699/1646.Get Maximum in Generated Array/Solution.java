class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            nums[i] = i % 2 == 0 ? nums[i >> 1] : nums[i >> 1] + nums[(i >> 1) + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }
}