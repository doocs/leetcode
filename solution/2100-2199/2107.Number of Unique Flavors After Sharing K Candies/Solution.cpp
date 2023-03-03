class Solution {
public:
    int shareCandies(vector<int>& candies, int k) {
        unordered_map<int, int> cnt;
        int n = candies.size();
        for (int i = k; i < n; ++i) {
            ++cnt[candies[i]];
        }
        int ans = cnt.size();
        for (int i = k; i < candies.size(); ++i) {
            if (--cnt[candies[i]] == 0) {
                cnt.erase(candies[i]);
            }
            ++cnt[candies[i - k]];
            ans = max(ans, (int) cnt.size());
        }
        return ans;
    }
};