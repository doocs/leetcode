class Solution {
    public int[] transformArray(int[] nums) {
        int even = 0;
        for (int x : nums) {
            even += (x & 1 ^ 1);
        }
        for (int i = 0; i < even; ++i) {
            nums[i] = 0;
        }
        for (int i = even; i < nums.length; ++i) {
            nums[i] = 1;
        }
        return nums;
    }
}
