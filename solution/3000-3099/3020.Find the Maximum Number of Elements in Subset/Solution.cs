public class Solution {
    public int MaximumLength(int[] nums) {
        Dictionary<long, int> cnt = new Dictionary<long, int>();
        foreach (int num in nums) {
            if (!cnt.ContainsKey(num)) {
                cnt[num] = 0;
            }
            cnt[num]++;
        }

        int ans = 0;
        if (cnt.ContainsKey(1)) {
            ans = cnt[1] - ((cnt[1] % 2) ^ 1);
            cnt.Remove(1);
        }

        foreach (long key in cnt.Keys) {
            long x = key;
            int t = 0;
            
            while (cnt.ContainsKey(x) && cnt[x] > 1) {
                x = x * x;
                t += 2;
            }
            
            t += (cnt.ContainsKey(x) && cnt[x] >= 1) ? 1 : -1;
            ans = Math.Max(ans, t);
        }

        return ans;
    }
}
