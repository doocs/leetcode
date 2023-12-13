class Solution {
public:
    int countHighestScoreNodes(vector<int>& parents) {
        int n = parents.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }
        int ans = 0;
        long long mx = 0;
        function<int(int, int)> dfs = [&](int i, int fa) {
            long long score = 1;
            int cnt = 1;
            for (int j : g[i]) {
                if (j != fa) {
                    int t = dfs(j, i);
                    cnt += t;
                    score *= t;
                }
            }
            if (n - cnt) {
                score *= n - cnt;
            }
            if (mx < score) {
                mx = score;
                ans = 1;
            } else if (mx == score) {
                ++ans;
            }
            return cnt;
        };
        dfs(0, -1);
        return ans;
    }
};