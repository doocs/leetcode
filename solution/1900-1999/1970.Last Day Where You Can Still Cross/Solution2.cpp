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

private:
    vector<int> p, size;
};

class Solution {
public:
    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        int mn = cells.size();
        UnionFind uf(mn + 2);
        int s = mn, t = mn + 1;
        vector<vector<int>> g(row, vector<int>(col, 1));
        const int dirs[5] = {0, 1, 0, -1, 0};
        for (int i = mn - 1;; --i) {
            int x = cells[i][0] - 1, y = cells[i][1] - 1;
            g[x][y] = 0;
            for (int j = 0; j < 4; ++j) {
                int nx = x + dirs[j], ny = y + dirs[j + 1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] == 0) {
                    uf.unite(x * col + y, nx * col + ny);
                }
            }
            if (x == 0) {
                uf.unite(s, x * col + y);
            }
            if (x == row - 1) {
                uf.unite(t, x * col + y);
            }
            if (uf.find(s) == uf.find(t)) {
                return i;
            }
        }
    }
};
