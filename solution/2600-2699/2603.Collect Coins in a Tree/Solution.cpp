class Solution {
public:
    int collectTheCoins(vector<int>& coins, vector<vector<int>>& edges) {
        int n = coins.size();
        unordered_set<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (coins[i] == 0 && g[i].size() == 1) {
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                g[j].erase(i);
                if (coins[j] == 0 && g[j].size() == 1) {
                    q.push(j);
                }
            }
            g[i].clear();
        }
        for (int k = 0; k < 2; ++k) {
            vector<int> q;
            for (int i = 0; i < n; ++i) {
                if (g[i].size() == 1) {
                    q.push_back(i);
                }
            }
            for (int i : q) {
                for (int j : g[i]) {
                    g[j].erase(i);
                }
                g[i].clear();
            }
        }
        int ans = 0;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (g[a].size() && g[b].size()) {
                ans += 2;
            }
        }
        return ans;
    }
};