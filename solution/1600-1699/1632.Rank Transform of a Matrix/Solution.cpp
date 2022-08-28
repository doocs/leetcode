class Solution {
public:
    vector<vector<int>> matrixRankTransform(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        map<int, vector<pair<int, int>>> d;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d[matrix[i][j]].push_back({i, j});
            }
        }
        vector<int> rowMax(m);
        vector<int> colMax(n);
        vector<vector<int>> ans(m, vector<int>(n));
        for (auto& [v, g] : d) {
            unordered_map<int, int> p;
            unordered_map<int, int> rank;
            for (auto [i, j] : g) {
                unite(i, j + 500, p);
            }
            for (auto [i, j] : g) {
                rank[find(i, p)] = max(rank[find(i, p)], max(rowMax[i], colMax[j]));
            }
            for (auto [i, j] : g) {
                ans[i][j] = rowMax[i] = colMax[j] = 1 + rank[find(i, p)];
            }
        }
        return ans;
    }

    void unite(int a, int b, unordered_map<int, int>& p) {
        p[find(a, p)] = find(b, p);
    }

    int find(int x, unordered_map<int, int>& p) {
        if (!p.count(x)) p[x] = x;
        if (p[x] != x) p[x] = find(p[x], p);
        return p[x];
    }
};