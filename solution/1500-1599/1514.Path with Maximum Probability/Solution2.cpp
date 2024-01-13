class Solution {
public:
    double maxProbability(int n, vector<vector<int>>& edges, vector<double>& succProb, int start, int end) {
        vector<vector<pair<int, double>>> g(n);
        for (int i = 0; i < edges.size(); ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].push_back({b, s});
            g[b].push_back({a, s});
        }
        vector<double> d(n);
        vector<bool> vis(n);
        d[start] = 1.0;
        queue<int> q{{start}};
        vis[start] = true;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            vis[i] = false;
            for (auto& ne : g[i]) {
                int j = ne.first;
                double s = ne.second;
                if (d[j] < d[i] * s) {
                    d[j] = d[i] * s;
                    if (!vis[j]) {
                        q.push(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return d[end];
    }
};