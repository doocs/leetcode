class Solution {
private:
    vector<vector<int>> g;
    int n;
    const int inf = INT_MAX / 2;

    vector<int> bfs(int i) {
        vector<int> dist(n, inf);
        queue<int> q;
        dist[i] = 0;
        q.push(i);
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        q.push(v);
                    }
                }
            }
        }
        return dist;
    }

public:
    int specialNodes(int n, vector<vector<int>>& edges, int x, int y, int z) {
        this->n = n;
        g.assign(n, {});
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }

        vector<int> d1 = bfs(x);
        vector<int> d2 = bfs(y);
        vector<int> d3 = bfs(z);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            array<long long, 3> a = {
                (long long) d1[i],
                (long long) d2[i],
                (long long) d3[i]};
            sort(a.begin(), a.end());
            if (a[0] * a[0] + a[1] * a[1] == a[2] * a[2]) {
                ++ans;
            }
        }
        return ans;
    }
};
