class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfWays(string corridor) {
        int n = corridor.size();
        vector<vector<int>> f(n, vector<int>(3, -1));
        function<int(int, int)> dfs;
        dfs = [&](int i, int cnt) {
            if (i == n) return cnt == 2 ? 1 : 0;
            cnt += corridor[i] == 'S';
            if (cnt > 2) return 0;
            if (f[i][cnt] != -1) return f[i][cnt];
            int ans = dfs(i + 1, cnt);
            if (cnt == 2) {
                ans += dfs(i + 1, 0);
                ans %= mod;
            }
            f[i][cnt] = ans;
            return ans;
        };
        return dfs(0, 0);
    }
};