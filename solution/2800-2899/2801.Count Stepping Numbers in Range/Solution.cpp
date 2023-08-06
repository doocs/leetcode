class Solution {
public:
    int countSteppingNumbers(string low, string high) {
        const int mod = 1e9 + 7;
        int m = high.size();
        int f[m + 1][10];
        memset(f, -1, sizeof(f));
        string num = high;

        function<int(int, int, bool, bool)> dfs = [&](int pos, int pre, bool lead, bool limit) {
            if (pos >= num.size()) {
                return lead ? 0 : 1;
            }
            if (!lead && !limit && f[pos][pre] != -1) {
                return f[pos][pre];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (i == 0 && lead) {
                    ans += dfs(pos + 1, pre, true, limit && i == up);
                } else if (pre == -1 || abs(pre - i) == 1) {
                    ans += dfs(pos + 1, i, false, limit && i == up);
                }
                ans %= mod;
            }
            if (!lead && !limit) {
                f[pos][pre] = ans;
            }
            return ans;
        };

        int a = dfs(0, -1, true, true);
        memset(f, -1, sizeof(f));
        for (int i = low.size() - 1; i >= 0; --i) {
            if (low[i] == '0') {
                low[i] = '9';
            } else {
                low[i] -= 1;
                break;
            }
        }
        num = low;
        int b = dfs(0, -1, true, true);
        return (a - b + mod) % mod;
    }
};