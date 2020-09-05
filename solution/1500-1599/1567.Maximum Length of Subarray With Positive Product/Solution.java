class Solution {
     public int getMaxLen(int[] nums) {
        // p[i] = n[i-1] + 1, nums[i] < 0
        // p[i] = p[i-1] + 1, nums[i] > 0
        // p[i] = 0, nums[i] = 0

        // n[i] = p[i-1] + 1, nums[i] < 0
        // n[i] = n[i-1] + 1, nums[i] > 0
        // n[i] = 0, nums[i] = 0
        int[] p = new int[nums.length];
        int[] n = new int[nums.length];
        p[0] = nums[0] > 0 ? 1 : 0;
        n[0] = nums[0] < 0 ? 1 : 0;
        int res = Math.max(p[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                p[i] = p[i - 1] + 1;
                n[i] = n[i - 1] == 0 ? 0 : n[i - 1] + 1;
            } else if (nums[i] == 0) {
                p[i] = 0;
                n[i] = 0;
            } else {
                p[i] = n[i - 1] == 0 ? 0 : n[i - 1] + 1;
                n[i] = p[i - 1] + 1;
            }
            res = Math.max(res, p[i]);
        }
        return res;
    }
}