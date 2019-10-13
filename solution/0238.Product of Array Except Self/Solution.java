class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        for (int i = 0, left = 1; i < len; i++) {
            output[i] = left;
            left *= nums[i];
        }
        for (int j = len - 1, right = 1; j >= 0; j--) {
            output[j] *= right;
            right *= nums[j];
        }
        return output;
    }
}