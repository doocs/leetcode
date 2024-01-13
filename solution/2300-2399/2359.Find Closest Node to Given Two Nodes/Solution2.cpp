class Solution {
public:
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        int n = edges.size();
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].push_back(edges[i]);
            }
        }
        const int inf = 1 << 30;
        using pii = pair<int, int>;
        auto f = [&](int i) {
            vector<int> dist(n, inf);
            dist[i] = 0;
            queue<int> q{{i}};
            while (!q.empty()) {
                i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if (dist[j] == inf) {
                        dist[j] = dist[i] + 1;
                        q.push(j);
                    }
                }
            }
            return dist;
        };
        vector<int> d1 = f(node1);
        vector<int> d2 = f(node2);
        int ans = -1, d = inf;
        for (int i = 0; i < n; ++i) {
            int t = max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }
};