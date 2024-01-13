class Solution {
public:
    int tallestBillboard(vector<int>& rods) {
        int s = accumulate(rods.begin(), rods.end(), 0);
        int n = rods.size();
        int f[n][s + 1];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= n) {
                return j == 0 ? 0 : -(1 << 30);
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = max(dfs(i + 1, j), dfs(i + 1, j + rods[i]));
            ans = max(ans, dfs(i + 1, abs(j - rods[i])) + min(j, rods[i]));
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};