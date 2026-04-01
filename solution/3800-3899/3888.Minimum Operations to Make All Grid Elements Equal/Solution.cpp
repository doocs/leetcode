class Solution {
public:
    long long minOperations(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        int mx = grid[0][0];
        for (auto& row : grid) {
            for (int val : row) {
                mx = max(mx, val);
            }
        }

        auto check = [&](int target) -> long long {
            vector<vector<long long>> diff(m + 2, vector<long long>(n + 2, 0));
            long long total_ops = 0;

            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                    long long cur_val = grid[i - 1][j - 1] + diff[i][j];

                    if (cur_val > target) {
                        return -1;
                    }

                    if (cur_val < target) {
                        if (i + k - 1 > m || j + k - 1 > n) {
                            return -1;
                        }

                        long long needed = target - cur_val;
                        total_ops += needed;
                        diff[i][j] += needed;
                        diff[i + k][j] -= needed;
                        diff[i][j + k] -= needed;
                        diff[i + k][j + k] += needed;
                    }
                }
            }

            return total_ops;
        };

        for (int t = mx; t <= mx + 1; ++t) {
            long long res = check(t);
            if (res != -1) {
                return res;
            }
        }

        return -1;
    }
};
