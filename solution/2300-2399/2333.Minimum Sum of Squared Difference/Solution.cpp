class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        int k = k1 + k2;
        long long s = 0;
        int mx = 0;
        vector<int> cnt(100001);
        for (int i = 0; i < nums1.size(); ++i) {
            int v = abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += 1LL * v * v * cnt[v];
        }
        return ans;
    }
};
