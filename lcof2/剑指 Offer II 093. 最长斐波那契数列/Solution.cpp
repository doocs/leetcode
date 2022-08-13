class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        unordered_map<int, int> mp;
        int n = arr.size();
        for (int i = 0; i < n; ++i) mp[arr[i]] = i;
        vector<vector<int>> dp(n, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j)
                dp[j][i] = 2;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int delta = arr[i] - arr[j];
                if (mp.count(delta)) {
                    int k = mp[delta];
                    if (k < j) {
                        dp[j][i] = dp[k][j] + 1;
                        ans = max(ans, dp[j][i]);
                    }
                }
            }
        }
        return ans;
    }
};