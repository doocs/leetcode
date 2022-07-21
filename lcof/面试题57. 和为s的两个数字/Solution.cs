public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        int p = 0, q = nums.Length - 1;
        while (p < q) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return new int[]{nums[p], nums[q]};
            }
            if (s < target) {
                p += 1;
            } else {
                q -= 1;
            }
        }
        return new int[]{};
    }
}