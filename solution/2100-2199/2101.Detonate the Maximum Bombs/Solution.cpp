class Solution {
public:
    int maximumDetonation(vector<vector<int>>& bombs) {
        int n = bombs.size();
        vector<vector<bool>> g(n, vector<bool>(n));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                g[i][j] = check(i, j, bombs);
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            queue<int> q{{k}};
            vector<bool> vis(n);
            vis[k] = true;
            int cnt = 0;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ++cnt;
                for (int j = 0; j < n; ++j) {
                    if (g[i][j] && !vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
            ans = max(ans, cnt);
        }
        return ans;
    }

    bool check(int i, int j, vector<vector<int>>& bombs) {
        if (i == j) return false;
        long long x = bombs[i][0] - bombs[j][0];
        long long y = bombs[i][1] - bombs[j][1];
        long long r = bombs[i][2];
        return r * r >= x * x + y * y;
    }
};