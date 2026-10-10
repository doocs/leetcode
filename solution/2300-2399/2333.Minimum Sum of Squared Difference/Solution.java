class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int k = k1 + k2;
        long s = 0;
        int mx = 0;
        int[] cnt = new int[100001];
        for (int i = 0; i < nums1.length; ++i) {
            int v = Math.abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = Math.max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = Math.min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += (long) v * v * cnt[v];
        }
        return ans;
    }
}
