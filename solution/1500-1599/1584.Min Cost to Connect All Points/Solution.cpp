class Solution {
public:
    vector<int> p;

    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < n; ++i)
        {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j)
            {
                int x2 = points[j][0], y2 = points[j][1];
                edges.push_back({abs(x1 - x2) + abs(y1 - y2), i, j});
            }
        }
        sort(edges.begin(), edges.end());
        int res = 0;
        for (auto e : edges)
        {
            if (find(e[1]) == find(e[2])) continue;
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) break;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};