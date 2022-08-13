class Solution {
public:
    int longestCycle(vector<int>& edges) {
        int n = edges.size();
        vector<int> vis(n);
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            int curr = i;
            vector<int> cycle;
            while (curr != -1 && !vis[curr]) {
                cycle.push_back(curr);
                vis[curr] = true;
                curr = edges[curr];
            }
            if (curr == -1) continue;
            for (int j = 0; j < cycle.size(); ++j) {
                if (cycle[j] == curr) {
                    ans = max(ans, (int)cycle.size() - j);
                    break;
                }
            }
        }
        return ans;
    }
};