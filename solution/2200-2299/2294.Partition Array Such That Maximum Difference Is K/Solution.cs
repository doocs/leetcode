public class Solution {
    public int PartitionArray(int[] nums, int k) {
        Array.Sort(nums);
        int ans = 1;
        int a = nums[0];

        foreach (int b in nums) {
            if (b - a > k) {
                a = b;
                ans++;
            }
        }

        return ans;
    }
}
