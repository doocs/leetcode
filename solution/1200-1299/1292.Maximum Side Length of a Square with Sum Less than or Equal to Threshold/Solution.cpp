class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> s(310, vector<int>(310));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + mat[i][j];
        int left = 0, right = min(m, n);
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid, s, m, n, threshold))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    bool check(int l, vector<vector<int>>& s, int m, int n, int threshold) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i + l - 1 < m && j + l - 1 < n) {
                    int i1 = i + l - 1, j1 = j + l - 1;
                    int t = s[i1 + 1][j1 + 1] - s[i1 + 1][j] - s[i][j1 + 1] + s[i][j];
                    if (t <= threshold) return true;
                }
            }
        }
        return false;
    }
};