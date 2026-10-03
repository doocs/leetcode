class Solution {
public:
    long long maxCoins(vector<int>& lane1, vector<int>& lane2) {
        int n = lane1.size();
        vector<vector<vector<long long>>> f(n + 1, vector<vector<long long>>(2, vector<long long>(3)));
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 0; k < 3; ++k) {
                for (int j = 0; j < 2; ++j) {
                    long long x = j == 0 ? lane1[i] : lane2[i];
                    long long ans = max(x, f[i + 1][j][k] + x);
                    if (k > 0) {
                        ans = max(ans, f[i + 1][j ^ 1][k - 1] + x);
                        ans = max(ans, f[i][j ^ 1][k - 1]);
                    }
                    f[i][j][k] = ans;
                }
            }
        }
        long long ans = f[0][0][2];
        for (int i = 1; i < n; ++i) {
            ans = max(ans, f[i][0][2]);
        }
        return ans;
    }
};
