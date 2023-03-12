class Solution {
public:
    long long beautifulSubarrays(vector<int>& nums) {
        unordered_map<int, int> cnt{{0, 1}};
        long long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt[mask];
            ++cnt[mask];
        }
        return ans;
    }
};