class Solution {
public:
    int longestCycle(vector<int>& edges) {
        int n = edges.size();
        vector<bool> vis(n);
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            int j = i;
            vector<int> cycle;
            for (; j != -1 && !vis[j]; j = edges[j]) {
                vis[j] = true;
                cycle.push_back(j);
            }
            if (j == -1) {
                continue;
            }
            for (int k = 0; k < cycle.size(); ++k) {
                if (cycle[k] == j) {
                    ans = max(ans, (int) cycle.size() - k);
                    break;
                }
            }
        }
        return ans;
    }
};