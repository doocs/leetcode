class Solution {
public:
    int maximumDetonation(vector<vector<int>>& bombs) {
        int n = bombs.size();
        vector<int> g[n];
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                auto& p1 = bombs[i];
                auto& p2 = bombs[j];
                auto dist = hypot(p1[0] - p2[0], p1[1] - p2[1]);
                if (dist <= p1[2]) {
                    g[i].push_back(j);
                }
                if (dist <= p2[2]) {
                    g[j].push_back(i);
                }
            }
        }
        int ans = 0;
        bool vis[n];
        for (int k = 0; k < n; ++k) {
            memset(vis, false, sizeof(vis));
            queue<int> q;
            q.push(k);
            vis[k] = true;
            int cnt = 0;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ++cnt;
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
            if (cnt == n) {
                return n;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};