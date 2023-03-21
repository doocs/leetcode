class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != 0x3f3f3f3f) {
                return f[i];
            }
            int t = 1 << 30;
            for (int j = 1; j < 4; ++j) {
                t = min(t, dfs(i + j));
            }
            return f[i] = s[n] - s[i] - t;
        };
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : (a > b ? "Alice" : "Bob");
    }
};