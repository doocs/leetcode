class Solution {
public:
    vector<int> circularGameLosers(int n, int k) {
        bool vis[n];
        memset(vis, false, sizeof(vis));
        for (int i = 0, p = 1; !vis[i]; ++p) {
            vis[i] = true;
            i = (i + p * k) % n;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};