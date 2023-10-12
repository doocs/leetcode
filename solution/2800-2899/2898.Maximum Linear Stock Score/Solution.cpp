class Solution {
public:
    long long maxScore(vector<int>& prices) {
        unordered_map<int, long long> cnt;
        for (int i = 0; i < prices.size(); ++i) {
            cnt[prices[i] - i] += prices[i];
        }
        long long ans = 0;
        for (auto& [_, v] : cnt) {
            ans = max(ans, v);
        }
        return ans;
    }
};