class Solution {
public:
    vector<int> p;

    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        vector<vector<int>> g;
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                g.push_back({abs(x1 - x2) + abs(y1 - y2), i, j});
            }
        }
        sort(g.begin(), g.end());
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int ans = 0;
        for (auto& e : g) {
            int cost = e[0], i = e[1], j = e[2];
            if (find(i) == find(j)) continue;
            p[find(i)] = find(j);
            ans += cost;
            if (--n == 1) return ans;
        }
        return 0;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};