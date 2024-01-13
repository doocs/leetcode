class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();
        int s[m + 1][n + 1];
        memset(s, 0, sizeof(s));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        auto check = [&](int k) {
            for (int i = 0; i < m - k + 1; ++i) {
                for (int j = 0; j < n - k + 1; ++j) {
                    if (s[i + k][j + k] - s[i][j + k] - s[i + k][j] + s[i][j] <= threshold) {
                        return true;
                    }
                }
            }
            return false;
        };
        int l = 0, r = min(m, n);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};