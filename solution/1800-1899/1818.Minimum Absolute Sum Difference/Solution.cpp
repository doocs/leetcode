class Solution {
public:
    int minAbsoluteSumDiff(vector<int>& nums1, vector<int>& nums2) {
        const int mod = 1e9 + 7;
        int n = nums1.size();
        vector<int> diff(n);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % mod;
        }
        if (s == 0) return 0;
        sort(nums1.begin(), nums1.end());
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) continue;
            int b = nums2[i];
            int idx = lower_bound(nums1.begin(), nums1.end(), b) - nums1.begin();
            int a1 = 1e6, a2 = 1e6;
            if (idx != n) a1 = nums1[idx];
            if (idx != 0) a2 = nums1[idx - 1];
            int c = min(abs(b - a1), abs(b - a2));
            mx = max(mx, d - c);
        }
        return (s - mx + mod) % mod;
    }
};