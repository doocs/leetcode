class Solution {
public:
    vector<int> p;

    int minimumEffortPath(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (i < m - 1) edges.push_back({abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                if (j < n - 1) edges.push_back({abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
            }
        }
        sort(edges.begin(), edges.end());
        for (auto e : edges)
        {
            int i = e[1], j = e[2];
            p[find(i)] = find(j);
            if (find(0) == find(m * n - 1)) return e[0];
        }
        return 0;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};