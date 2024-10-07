class Solution {
public:
    vector<vector<int>> constructGridLayout(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }

        vector<int> deg(5, -1);
        for (int x = 0; x < n; ++x) {
            deg[g[x].size()] = x;
        }

        vector<int> row;
        if (deg[1] != -1) {
            row.push_back(deg[1]);
        } else if (deg[4] == -1) {
            int x = deg[2];
            for (int y : g[x]) {
                if (g[y].size() == 2) {
                    row.push_back(x);
                    row.push_back(y);
                    break;
                }
            }
        } else {
            int x = deg[2];
            row.push_back(x);
            int pre = x;
            x = g[x][0];
            while (g[x].size() > 2) {
                row.push_back(x);
                for (int y : g[x]) {
                    if (y != pre && g[y].size() < 4) {
                        pre = x;
                        x = y;
                        break;
                    }
                }
            }
            row.push_back(x);
        }

        vector<vector<int>> ans;
        ans.push_back(row);
        vector<bool> vis(n, false);
        int rowSize = row.size();
        for (int i = 0; i < n / rowSize - 1; ++i) {
            for (int x : row) {
                vis[x] = true;
            }
            vector<int> nxt;
            for (int x : row) {
                for (int y : g[x]) {
                    if (!vis[y]) {
                        nxt.push_back(y);
                        break;
                    }
                }
            }
            ans.push_back(nxt);
            row = nxt;
        }

        return ans;
    }
};
