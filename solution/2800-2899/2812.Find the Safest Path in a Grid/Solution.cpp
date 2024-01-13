class UnionFind {
public:
    vector<int> p;
    int n;

    UnionFind(int _n)
        : n(_n)
        , p(_n) {
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        p[pa] = pb;
        --n;
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};

class Solution {
public:
    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();
        if (grid[0][0] || grid[n - 1][n - 1]) {
            return 0;
        }
        queue<pair<int, int>> q;
        int dist[n][n];
        memset(dist, 0x3f, sizeof(dist));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    dist[i][j] = 0;
                    q.emplace(i, j);
                }
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == 0x3f3f3f3f) {
                    dist[x][y] = dist[i][j] + 1;
                    q.emplace(x, y);
                }
            }
        }
        vector<tuple<int, int, int>> t;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                t.emplace_back(dist[i][j], i, j);
            }
        }
        sort(t.begin(), t.end());
        reverse(t.begin(), t.end());
        UnionFind uf(n * n);
        for (auto [d, i, j] : t) {
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d) {
                    uf.unite(i * n + j, x * n + y);
                }
            }
            if (uf.find(0) == uf.find(n * n - 1)) {
                return d;
            }
        }
        return 0;
    }
};