class Solution {
public:
    long long countExcellentPairs(vector<int>& nums, int k) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> cnt(32);
        for (int v : s) ++cnt[__builtin_popcount(v)];
        long long ans = 0;
        for (int v : s) {
            int t = __builtin_popcount(v);
            for (int i = 0; i < 32; ++i) {
                if (t + i >= k) {
                    ans += cnt[i];
                }
            }
        }
        return ans;
    }
};