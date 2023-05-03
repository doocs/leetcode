class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int f[n];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != 0x3f3f3f3f) {
                return f[i];
            }
            int ans = -(1 << 30), s = 0;
            for (int j = 0; j < 3 && i + j < n; ++j) {
                s += stoneValue[i + j];
                ans = max(ans, s - dfs(i + j + 1));
            }
            return f[i] = ans;
        };
        int ans = dfs(0);
        if (ans == 0) {
            return "Tie";
        }
        return ans > 0 ? "Alice" : "Bob";
    }
};