class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; ++i) {
            int t = twoSumClosest(nums, i + 1, n - 1, target - nums[i]);
            if (Math.abs(nums[i] + t - target) < diff) {
                res = nums[i] + t;
                diff = Math.abs(nums[i] + t - target);
            }
        }
        return res;
    }

    private int twoSumClosest(int[] nums, int start, int end, int target) {
        int res = 0;
        int diff = Integer.MAX_VALUE;
        while (start < end) {
            int val = nums[start] + nums[end];
            if (val == target) {
                return val;
            }
            if (Math.abs(val - target) < diff) {
                res = val;
                diff = Math.abs(val - target);
            }
            if (val < target) {
                ++start;
            } else {
                --end;
            }
        }
        return res;
    }
}