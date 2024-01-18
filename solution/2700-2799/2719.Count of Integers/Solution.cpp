class Solution {
public:
    int count(string num1, string num2, int min_sum, int max_sum) {
        const int mod = 1e9 + 7;
        int f[23][220];
        memset(f, -1, sizeof(f));
        string num = num2;

        function<int(int, int, bool)> dfs = [&](int pos, int s, bool limit) -> int {
            if (pos >= num.size()) {
                return s >= min_sum && s <= max_sum ? 1 : 0;
            }
            if (!limit && f[pos][s] != -1) {
                return f[pos][s];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos + 1, s + i, limit && i == up);
                ans %= mod;
            }
            if (!limit) {
                f[pos][s] = ans;
            }
            return ans;
        };

        int a = dfs(0, 0, true);
        for (int i = num1.size() - 1; ~i; --i) {
            if (num1[i] == '0') {
                num1[i] = '9';
            } else {
                num1[i] -= 1;
                break;
            }
        }
        num = num1;
        memset(f, -1, sizeof(f));
        int b = dfs(0, 0, true);
        return (a - b + mod) % mod;
    }
};