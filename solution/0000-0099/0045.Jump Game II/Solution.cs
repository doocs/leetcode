public class Solution {
    public int Jump(int[] nums) {
        int end = 0;
        int mx = 0;
        int steps = 0;
        for (int i = 0; i < nums.Length - 1; ++i)
        {
            mx = Math.Max(mx, i + nums[i]);
            if (i == end)
            {
                end = mx;
                ++steps;
            }
        }
        return steps;
    }
}