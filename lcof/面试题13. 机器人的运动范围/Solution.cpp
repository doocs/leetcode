class Solution {
public:
    int m;
    int n;
    int k;
    vector<vector<bool>> vis;

    int movingCount(int m, int n, int k) {
        this->m = m;
        this->n = n;
        this->k = k;
        vis.resize(m, vector<bool>(n, false));
        return dfs(0, 0);
    }

    int dfs(int i, int j) {
        if (i >= m || j >= n || vis[i][j] || (i % 10 + i / 10 + j % 10 + j / 10) > k) return 0;
        vis[i][j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    }
};