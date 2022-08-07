class Solution {
public:
    long long countBadPairs(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n; ++i) nums[i] = i - nums[i];
        unordered_map<int, int> cnt;
        for (int v : nums) cnt[v]++;
        long long ans = 0;
        for (auto [_, v] : cnt) ans += 1ll * v * (n - v);
        return ans >> 1;
    }
};