class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cnt = 1;
        int res = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                res = Math.max(res, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return res;
    }
}
