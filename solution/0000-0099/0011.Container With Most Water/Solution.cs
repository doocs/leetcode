public class Solution {
    public int MaxArea(int[] height) {
        int l = 0, r = height.Length - 1;
        int ans = 0;
        while (l < r) {
            int t = Math.Min(height[l], height[r]) * (r - l);
            ans = Math.Max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
