class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.size();
        vector<unordered_set<int>> s(n);
        vector<vector<int>> g(n);
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            for (int v : routes[i]) {
                s[i].insert(v);
                d[v].push_back(i);
            }
        }
        for (auto& [_, ids] : d) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = ids[i], b = ids[j];
                    g[a].push_back(b);
                    g[b].push_back(a);
                }
            }
        }
        queue<int> q;
        unordered_set<int> vis;
        int ans = 1;
        for (int v : d[source]) {
            q.push(v);
            vis.insert(v);
        }
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                if (s[i].count(target)) {
                    return ans;
                }
                for (int j : g[i]) {
                    if (!vis.count(j)) {
                        vis.insert(j);
                        q.push(j);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};