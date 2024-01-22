class Solution {
    public int minimumCost(int[] nums) {
        int a = nums[0], b = 100, c = 100;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < b) {
                c = b;
                b = nums[i];
            } else if (nums[i] < c) {
                c = nums[i];
            }
        }
        return a + b + c;
    }
}