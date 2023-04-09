class Solution {
public:
    vector<int> gardenNoAdj(int n, vector<vector<int>>& paths) {
        vector<vector<int>> g(n);
        for (auto& p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].push_back(y);
            g[y].push_back(x);
        }
        vector<int> ans(n);
        bool used[5];
        for (int x = 0; x < n; ++x) {
            memset(used, false, sizeof(used));
            for (int y : g[x]) {
                used[ans[y]] = true;
            }
            for (int c = 1; c < 5; ++c) {
                if (!used[c]) {
                    ans[x] = c;
                    break;
                }
            }
        }
        return ans;
    }
};