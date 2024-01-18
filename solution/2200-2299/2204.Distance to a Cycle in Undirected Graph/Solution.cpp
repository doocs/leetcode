class Solution {
public:
    vector<int> distanceToCycle(int n, vector<vector<int>>& edges) {
        unordered_set<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (g[i].size() == 1) {
                q.push(i);
            }
        }
        int f[n];
        int seq[n];
        int k = 0;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            seq[k++] = i;
            for (int j : g[i]) {
                g[j].erase(i);
                f[i] = j;
                if (g[j].size() == 1) {
                    q.push(j);
                }
            }
            g[i].clear();
        }
        vector<int> ans(n);
        for (; k; --k) {
            int i = seq[k - 1];
            ans[i] = ans[f[i]] + 1;
        }
        return ans;
    }
};