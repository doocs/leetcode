class Solution {
public:
    int minimumCost(vector<int>& start, vector<int>& target, vector<vector<int>>& specialRoads) {
        auto dist = [](int x1, int y1, int x2, int y2) {
            return abs(x1 - x2) + abs(y1 - y2);
        };
        int ans = 1 << 30;
        int n = 1e6;
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        pq.push({0, start[0], start[1]});
        unordered_set<long long> vis;
        while (!pq.empty()) {
            auto [d, x, y] = pq.top();
            pq.pop();
            long long k = 1LL * x * n + y;
            if (vis.count(k)) {
                continue;
            }
            vis.insert(k);
            ans = min(ans, d + dist(x, y, target[0], target[1]));
            for (auto& r : specialRoads) {
                int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3], cost = r[4];
                pq.push({d + dist(x, y, x1, y1) + cost, x2, y2});
            }
        }
        return ans;
    }
};