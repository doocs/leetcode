class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (((String.valueOf(nums[i]).length()) & 1) == 0) {
                res++;
            }
        }
        return res;
    }
}
