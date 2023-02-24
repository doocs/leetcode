class Solution {
public:
    long long countBadPairs(vector<int>& nums) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int x = i - nums[i];
            ans += i - cnt[x];
            ++cnt[x];
        }
        return ans;
    }
};