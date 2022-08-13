class Solution {
public:
    vector<vector<int>> matrixBlockSum(vector<vector<int>>& mat, int k) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> pre(m + 1, vector<int>(n + 1));
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + -pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1, m, n, pre) - get(i + k + 1, j - k, m, n, pre) - get(i - k, j + k + 1, m, n, pre) + get(i - k, j - k, m, n, pre);
            }
        }
        return ans;
    }

    int get(int i, int j, int m, int n, vector<vector<int>>& pre) {
        i = max(min(m, i), 0);
        j = max(min(n, j), 0);
        return pre[i][j];
    }
};