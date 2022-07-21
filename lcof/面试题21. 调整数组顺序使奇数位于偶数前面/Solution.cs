public class Solution {
    public int[] Excahnge(int[] nums) {
        int p = 0, q = nums.Length - 1;
        while (p < q) {
            if (nums[p] % 2 == 1) {
                p += 1;
                continue;
            }
            if (nums[q] % 2 == 0) {
                q -= 1;
                continue;
            }
            nums[p] = nums[p] + nums[q];
            nums[q] = nums[p] - nums[q];
            nums[p] = nums[p] - nums[q];
        }
        return nums;
    }
}