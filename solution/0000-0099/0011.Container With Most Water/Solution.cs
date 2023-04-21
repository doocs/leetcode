public class Solution {
    public int MaxArea(int[] height) {
        int i = 0, j = height.Length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.Min(height[i], height[j]) * (j - i);
            ans = Math.Max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}