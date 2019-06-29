class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int sum = Arrays.stream(nums).sum();
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (s << 1 == sum - nums[i]) {
                return i;
            }
            s += nums[i];
        }
        return -1;
    }
}
