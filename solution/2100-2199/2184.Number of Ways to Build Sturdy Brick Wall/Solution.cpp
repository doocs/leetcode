class Solution {
public:
    vector<int> bricks;
    int width;
    int mod = 1e9 + 7;
    vector<vector<int>> res;
    vector<int> t;

    int buildWall(int height, int width, vector<int>& bricks) {
        this->width = width;
        this->bricks = bricks;
        dfs(0);
        t.resize(0);
        int n = res.size();
        vector<vector<int>> g(n);
        for (int i = 0; i < n; ++i) {
            if (check(res[i], res[i])) {
                g[i].push_back(i);
            }
            for (int j = i + 1; j < n; ++j) {
                if (check(res[i], res[j])) {
                    g[i].push_back(j);
                    g[j].push_back(i);
                }
            }
        }
        vector<vector<int>> dp(height, vector<int>(n));
        for (int j = 0; j < n; ++j) dp[0][j] = 1;
        for (int i = 1; i < height; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= mod;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans += dp[height - 1][j];
            ans %= mod;
        }
        return ans;
    }

    bool check(vector<int>& a, vector<int>& b) {
        int s1 = a[0], s2 = b[0];
        int i = 1, j = 1;
        while (i < a.size() && j < b.size()) {
            if (s1 == s2) return false;
            if (s1 < s2)
                s1 += a[i++];
            else
                s2 += b[j++];
        }
        return true;
    }

    void dfs(int v) {
        if (v > width) return;
        if (v == width) {
            res.push_back(t);
            return;
        }
        for (int x : bricks) {
            t.push_back(x);
            dfs(v + x);
            t.pop_back();
        }
    }
};