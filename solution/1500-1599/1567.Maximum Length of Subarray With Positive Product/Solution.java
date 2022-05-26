class Solution {
    public int getMaxLen(int[] nums) {
        int f1 = nums[0] > 0 ? 1 : 0;
        int f2 = nums[0] < 0 ? 1 : 0;
        int res = f1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                ++f1;
                f2 = f2 > 0 ? f2 + 1 : 0;
            } else if (nums[i] < 0) {
                int pf1 = f1, pf2 = f2;
                f2 = pf1 + 1;
                f1 = pf2 > 0 ? pf2 + 1 : 0;
            } else {
                f1 = 0;
                f2 = 0;
            }
            res = Math.max(res, f1);
        }
        return res;
    }
}