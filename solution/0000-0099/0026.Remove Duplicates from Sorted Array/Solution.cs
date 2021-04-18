public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int cnt = 0;
        int n = nums.Length;
        for (int i = 1; i < n; ++i)
        {
            if (nums[i] == nums[i - 1])
            {
                ++cnt;
            }
            else
            {
                nums[i - cnt] = nums[i];
            }
        }
        return n - cnt;
    }
}