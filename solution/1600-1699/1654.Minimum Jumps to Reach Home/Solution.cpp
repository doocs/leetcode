class Solution {
public:
    int minimumJumps(vector<int>& forbidden, int a, int b, int x) {
        unordered_set<int> s(forbidden.begin(), forbidden.end());
        queue<pair<int, int>> q;
        q.emplace(0, 1);
        const int n = 6000;
        bool vis[n][2];
        memset(vis, false, sizeof(vis));
        vis[0][1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto [i, k] = q.front();
                q.pop();
                if (i == x) {
                    return ans;
                }
                vector<pair<int, int>> nxts = {{i + a, 1}};
                if (k & 1) {
                    nxts.emplace_back(i - b, 0);
                }
                for (auto [j, l] : nxts) {
                    if (j >= 0 && j < n && !s.count(j) && !vis[j][l]) {
                        vis[j][l] = true;
                        q.emplace(j, l);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};