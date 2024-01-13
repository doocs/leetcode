class Solution {
public:
    int extractMantra(vector<string>& matrix, string mantra) {
        int m = matrix.size(), n = matrix[0].size();
        int l = mantra.size();
        queue<tuple<int, int, int>> q;
        q.push({0, 0, 0});
        bool vis[m][n][l + 1];
        memset(vis, 0, sizeof(vis));
        int dirs[5] = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (; q.size(); ++ans) {
            for (int size = q.size(); size; --size) {
                auto [i, j, k] = q.front();
                q.pop();
                if (k == l) {
                    return ans;
                }
                if (matrix[i][j] == mantra[k] && !vis[i][j][k + 1]) {
                    vis[i][j][k + 1] = true;
                    q.push({i, j, k + 1});
                } else {
                    for (int c = 0; c < 4; ++c) {
                        int x = i + dirs[c], y = j + dirs[c + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                            vis[x][y][k] = true;
                            q.push({x, y, k});
                        }
                    }
                }
            }
        }
        return -1;
    }
};