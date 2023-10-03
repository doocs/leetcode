class Solution {
public:
    vector<int> countVisitedNodes(vector<int>& edges) {
        int n = edges.size();
        vector<int> ans(n), vis(n);
        for (int i = 0; i < n; ++i) {
            if (!ans[i]) {
                int cnt = 0, j = i;
                while (vis[j] == 0) {
                    vis[j] = ++cnt;
                    j = edges[j];
                }
                int cycle = 0, total = cnt + ans[j];
                if (ans[j] == 0) {
                    cycle = cnt - vis[j] + 1;
                }
                j = i;
                while (ans[j] == 0) {
                    ans[j] = max(total--, cycle);
                    j = edges[j];
                }
            }
        }
        return ans;
    }
};