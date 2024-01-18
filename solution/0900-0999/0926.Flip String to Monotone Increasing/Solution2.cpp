class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '1');
        int ans = presum[n];
        for (int i = 0; i < n; ++i) ans = min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        return ans;
    }
};