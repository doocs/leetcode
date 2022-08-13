class Solution {
public:
    vector<int> p;
    vector<int> size;
    int n, mx;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int largestIsland(vector<vector<int>>& grid) {
        n = grid.size();
        mx = 1;
        p.resize(n * n);
        size.resize(n * n);
        for (int i = 0; i < p.size(); ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) unite(i * n + j, (i + e[0]) * n + j + e[1]);
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    unordered_set<int> s;
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.count(root)) {
                                t += size[root];
                                s.insert(root);
                            }
                        }
                    }
                    res = max(res, t);
                }
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
};