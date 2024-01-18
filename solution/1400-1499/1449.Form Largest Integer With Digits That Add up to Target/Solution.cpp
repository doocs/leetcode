class Solution {
public:
    string largestNumber(vector<int>& cost, int target) {
        const int inf = 1 << 30;
        vector<vector<int>> f(10, vector<int>(target + 1, -inf));
        vector<vector<int>> g(10, vector<int>(target + 1));
        f[0][0] = 0;
        for (int i = 1; i <= 9; ++i) {
            int c = cost[i - 1];
            for (int j = 0; j <= target; ++j) {
                if (j < c || f[i][j - c] + 1 < f[i - 1][j]) {
                    f[i][j] = f[i - 1][j];
                    g[i][j] = j;
                } else {
                    f[i][j] = f[i][j - c] + 1;
                    g[i][j] = j - c;
                }
            }
        }
        if (f[9][target] < 0) {
            return "0";
        }
        string ans;
        for (int i = 9, j = target; i;) {
            if (g[i][j] == j) {
                --i;
            } else {
                ans += '0' + i;
                j = g[i][j];
            }
        }
        return ans;
    }
};