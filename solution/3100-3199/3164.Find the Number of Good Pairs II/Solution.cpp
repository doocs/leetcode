class Solution {
public:
    long long numberOfPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        unordered_map<int, int> cnt1;
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1[x / k]++;
            }
        }
        if (cnt1.empty()) {
            return 0;
        }
        unordered_map<int, int> cnt2;
        for (int x : nums2) {
            ++cnt2[x];
        }
        int mx = 0;
        for (auto& [x, _] : cnt1) {
            mx = max(mx, x);
        }
        long long ans = 0;
        for (auto& [x, v] : cnt2) {
            long long s = 0;
            for (int y = x; y <= mx; y += x) {
                s += cnt1[y];
            }
            ans += s * v;
        }
        return ans;
    }
};