class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges, int k) {
        sort(edges.begin(), edges.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[2] < b[2];
            });

        auto check = [&](int idx) -> bool {
            vector<vector<int>> g(n);
            for (int i = 0; i <= idx; ++i) {
                int u = edges[i][0], v = edges[i][1];
                g[u].push_back(v);
                g[v].push_back(u);
            }

            vector<int> q;
            q.push_back(0);
            vector<char> vis(n, 0);
            vis[0] = 1;

            int dist = 0;
            while (!q.empty()) {
                vector<int> nq;
                for (int u : q) {
                    if (u == n - 1) {
                        return dist <= k;
                    }
                    for (int v : g[u]) {
                        if (!vis[v]) {
                            vis[v] = 1;
                            nq.push_back(v);
                        }
                    }
                }
                q.swap(nq);
                ++dist;
            }
            return false;
        };

        int m = edges.size();
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return check(l) ? edges[l][2] : -1;
    }
};
