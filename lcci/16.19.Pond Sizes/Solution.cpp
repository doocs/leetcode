class Solution {
public:
    vector<int> p;
    vector<int> size;

    vector<int> pondSizes(vector<vector<int>>& land) {
        int m = land.size(), n = land[0].size();
        for (int i = 0; i < m * n; ++i) {
            p.push_back(i);
            size.push_back(1);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] != 0) continue;
                int idx = i * n + j;
                if (i < m - 1 && land[i + 1][j] == 0) unite(idx, (i + 1) * n + j);
                if (j < n - 1 && land[i][j + 1] == 0) unite(idx, i * n + j + 1);
                if (i < m - 1 && j < n - 1 && land[i + 1][j + 1] == 0) unite(idx, (i + 1) * n + j + 1);
                if (i < m - 1 && j > 0 && land[i + 1][j - 1] == 0) unite(idx, (i + 1) * n + j - 1);
            }
        }
        unordered_set<int> s;
        vector<int> res;
        for (int i = 0; i < m * n; ++i) {
            if (land[i / n][i % n] != 0) continue;
            int root = find(i);
            if (s.find(root) == s.end()) {
                s.insert(root);
                res.push_back(size[root]);
            }
        }
        sort(res.begin(), res.end());
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        size[pb] += size[pa];
        p[pa] = pb;
    }
};