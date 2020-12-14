class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int sub1 = robInternal(Arrays.copyOfRange(nums, 0, n - 1));
        int sub2 = robInternal(Arrays.copyOfRange(nums, 1, n));
        return Math.max(sub1, sub2);
    }

    private int robInternal(int[] nums) {
        int n;
        if ((n = nums.length) == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int pre = nums[0];
        int cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i) {
            int t = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = t;
        }
        return cur;
    }
}