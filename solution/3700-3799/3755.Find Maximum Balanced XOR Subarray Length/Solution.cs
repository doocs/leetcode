public class Solution {
    public int MaxBalancedSubarray(int[] nums) {
        var d = new Dictionary<long, int>();
        int ans = 0;
        int a = 0;
        int b = nums.Length;

        d[(long)b] = -1;

        for (int i = 0; i < nums.Length; i++) {
            a ^= nums[i];
            b += nums[i] % 2 == 0 ? 1 : -1;

            long key = ((long)a << 32) | (uint)b;
            if (d.ContainsKey(key)) {
                ans = Math.Max(ans, i - d[key]);
            } else {
                d[key] = i;
            }
        }

        return ans;
    }
}