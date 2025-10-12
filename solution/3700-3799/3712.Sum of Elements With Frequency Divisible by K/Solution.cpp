class Solution {
public:
    int sumDivisibleByK(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            if (v % k == 0) {
                ans += x * v;
            }
        }
        return ans;
    }
};
