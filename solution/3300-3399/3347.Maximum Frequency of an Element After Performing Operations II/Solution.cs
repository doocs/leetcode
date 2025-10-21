public class Solution {
    public int MaxFrequency(int[] nums, int k, int numOperations) {
        var cnt = new Dictionary<int, int>();
        var d = new SortedDictionary<int, int>();

        foreach (var x in nums) {
            if (!cnt.ContainsKey(x)) {
                cnt[x] = 0;
            }
            cnt[x]++;

            if (!d.ContainsKey(x)) {
                d[x] = 0;
            }
            if (!d.ContainsKey(x - k)) {
                d[x - k] = 0;
            }
            if (!d.ContainsKey(x + k + 1)) {
                d[x + k + 1] = 0;
            }

            d[x - k] += 1;
            d[x + k + 1] -= 1;
        }

        int ans = 0, s = 0;
        foreach (var kvp in d) {
            int x = kvp.Key, t = kvp.Value;
            s += t;
            int cur = Math.Min(s, (cnt.ContainsKey(x) ? cnt[x] : 0) + numOperations);
            ans = Math.Max(ans, cur);
        }

        return ans;
    }
}
