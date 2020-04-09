class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] res = new int[n];
        res[0] = 1;
        res[1] = nums[1] > nums[0] ? 2 : 1;
        int max = res[1];
        for (int i = 2; i < n; ++i) {
            res[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                }
            }
            max = Math.max(max, res[i]);
        }
        return max;
    }
}