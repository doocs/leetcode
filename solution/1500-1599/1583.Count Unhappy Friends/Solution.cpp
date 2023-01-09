class Solution {
public:
    int unhappyFriends(int n, vector<vector<int>>& preferences, vector<vector<int>>& pairs) {
        int d[n][n];
        int p[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        for (auto& e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            int find = 0;
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                if (d[u][x] < d[u][p[u]]) {
                    find = 1;
                    break;
                }
            }
            ans += find;
        }
        return ans;
    }
};