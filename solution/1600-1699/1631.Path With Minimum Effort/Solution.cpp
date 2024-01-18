class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    bool connected(int a, int b) {
        return find(a) == find(b);
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        vector<array<int, 3>> edges;
        int dirs[3] = {0, 1, 0};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 2; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        edges.push_back({abs(heights[i][j] - heights[x][y]), i * n + j, x * n + y});
                    }
                }
            }
        }
        sort(edges.begin(), edges.end());
        UnionFind uf(m * n);
        for (auto& [h, a, b] : edges) {
            uf.unite(a, b);
            if (uf.connected(0, m * n - 1)) {
                return h;
            }
        }
        return 0;
    }
};