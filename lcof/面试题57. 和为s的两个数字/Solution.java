class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int p = 0, q = nums.length - 1; p < q;) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return new int[]{nums[p], nums[q]};
            }
            if (s < target) {
                ++p;
            } else {
                --q;
            }
        }
        return null;
    }
}