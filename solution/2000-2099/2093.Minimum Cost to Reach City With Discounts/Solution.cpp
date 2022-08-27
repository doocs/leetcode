class Solution {
public:
    int minimumCost(int n, vector<vector<int>>& highways, int discounts) {
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : highways) {
            int a = e[0], b = e[1], c = e[2];
            g[a].push_back({b, c});
            g[b].push_back({a, c});
        }
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> q;
        q.push({0, 0, 0});
        vector<vector<int>> dist(n, vector<int>(discounts + 1, INT_MAX));
        while (!q.empty()) {
            auto [cost, i, k] = q.top();
            q.pop();
            if (k > discounts || dist[i][k] <= cost) continue;
            if (i == n - 1) return cost;
            dist[i][k] = cost;
            for (auto [j, v] : g[i]) {
                q.push({cost + v, j, k});
                q.push({cost + v / 2, j, k + 1});
            }
        }
        return -1;
    }
};