public class Solution {
    public int MaxSubArrayLen(int[] nums, int k) {
        var d = new Dictionary<int, int>();
        d[0] = -1;
        int ans = 0;
        int s = 0;
        for (int i = 0; i < nums.Length; i++) {
            s += nums[i];
            if (d.ContainsKey(s - k)) {
                ans = Math.Max(ans, i - d[s - k]);
            }
            if (!d.ContainsKey(s)) {
                d[s] = i;
            }
        }
        return ans;
    }
}
