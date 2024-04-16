class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j; --k) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a > b) {
                ans[k] = a;
                ++i;
            } else {
                ans[k] = b;
                --j;
            }
        }
        return ans;
    }
}