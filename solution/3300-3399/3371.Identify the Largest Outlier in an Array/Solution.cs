public class Solution {
    public int GetLargestOutlier(int[] nums) {
        int s = 0;
        var cnt = new Dictionary<int, int>();
        foreach (int x in nums) {
            s += x;
            if (!cnt.ContainsKey(x)) cnt[x] = 0;
            cnt[x]++;
        }

        int ans = int.MinValue;
        foreach (var kv in cnt) {
            int x = kv.Key, v = kv.Value;
            int t = s - x;
            if (t % 2 != 0) continue;
            int y = t / 2;
            if (cnt.ContainsKey(y)) {
                if (x != y || v > 1) {
                    ans = Math.Max(ans, x);
                }
            }
        }
        return ans;
    }
}
