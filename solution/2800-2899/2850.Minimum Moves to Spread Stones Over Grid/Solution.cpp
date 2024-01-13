class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        using pii = pair<int, int>;
        vector<pii> left, right;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.emplace_back(i, j);
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.emplace_back(i, j);
                    }
                }
            }
        }
        auto cal = [](pii a, pii b) {
            return abs(a.first - b.first) + abs(a.second - b.second);
        };
        int n = left.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = __builtin_popcount(i);
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
};