class Solution {
public:
    const vector<int> dirs = {-1, 0, 1, 0, -1};
    vector<int> c;
    vector<vector<int>> areas;
    vector<unordered_set<int>> boundaries;
    vector<vector<int>> infected;
    vector<vector<bool>> vis;
    int m;
    int n;

    int containVirus(vector<vector<int>>& isInfected) {
        infected = isInfected;
        m = infected.size();
        n = infected[0].size();
        vis.assign(m, vector<bool>(n));
        int ans = 0;
        while (1) {
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) vis[i][j] = false;
            c.clear();
            areas.clear();
            boundaries.clear();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (infected[i][j] == 1 && !vis[i][j]) {
                        c.push_back(0);
                        areas.push_back({});
                        boundaries.push_back({});
                        dfs(i, j);
                    }
                }
            }
            if (areas.empty()) break;
            int idx = getMax();
            ans += c[idx];
            for (int t = 0; t < areas.size(); ++t) {
                if (t == idx) {
                    for (int v : areas[t]) {
                        int i = v / n, j = v % n;
                        infected[i][j] = -1;
                    }
                } else {
                    for (int v : areas[t]) {
                        int i = v / n, j = v % n;
                        for (int k = 0; k < 4; ++k) {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (x >= 0 && x < m && y >= 0 && y < n && infected[x][y] == 0) infected[x][y] = 1;
                        }
                    }
                }
            }
        }
        return ans;
    }

    int getMax() {
        int idx = 0;
        int mx = boundaries[0].size();
        for (int i = 1; i < boundaries.size(); ++i) {
            int t = boundaries[i].size();
            if (mx < t) {
                mx = t;
                idx = i;
            }
        }
        return idx;
    }

    void dfs(int i, int j) {
        vis[i][j] = true;
        areas.back().push_back(i * n + j);
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (infected[x][y] == 1 && !vis[x][y])
                    dfs(x, y);
                else if (infected[x][y] == 0) {
                    c.back() += 1;
                    boundaries.back().insert(x * n + y);
                }
            }
        }
    }
};