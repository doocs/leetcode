class Solution {
public:
    int minimumOperationsToWriteY(vector<vector<int>>& grid) {
        int n = grid.size();
        int cnt1[3]{};
        int cnt2[3]{};
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                bool a = i == j && i <= n / 2;
                bool b = i + j == n - 1 && i <= n / 2;
                bool c = j == n / 2 && i >= n / 2;
                if (a || b || c) {
                    ++cnt1[grid[i][j]];
                } else {
                    ++cnt2[grid[i][j]];
                }
            }
        }
        int ans = n * n;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i != j) {
                    ans = min(ans, n * n - cnt1[i] - cnt2[j]);
                }
            }
        }
        return ans;
    }
};