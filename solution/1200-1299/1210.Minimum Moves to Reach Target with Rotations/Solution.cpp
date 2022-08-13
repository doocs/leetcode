class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> target = {n * n - 2, n * n - 1};
        queue<vector<int>> q;
        q.push({0, 1});
        vector<vector<bool>> vis(n * n, vector<bool>(n * n));
        int ans = 0;
        vis[0][1] = true;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                auto p = q.front();
                if (p == target) return ans;
                q.pop();
                int a = p[0], b = p[1];
                int i1 = a / n, j1 = a % n;
                int i2 = b / n, j2 = b % n;
                if (j1 + 1 < n && j2 + 1 < n && grid[i1][j1 + 1] == 0 && grid[i2][j2 + 1] == 0) {
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1, q, vis);
                    if (j1 == j2) check(a, i1 * n + j2 + 1, q, vis);
                }
                if (i1 + 1 < n && i2 + 1 < n && grid[i1 + 1][j1] == 0 && grid[i2 + 1][j2] == 0) {
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2, q, vis);
                    if (i1 == i2) check(a, (i2 + 1) * n + j1, q, vis);
                }
            }
            ++ans;
        }
        return -1;
    }

    void check(int a, int b, queue<vector<int>>& q, vector<vector<bool>>& vis) {
        if (!vis[a][b]) {
            vis[a][b] = true;
            q.push({a, b});
        }
    }
};