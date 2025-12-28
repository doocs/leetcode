class Solution {
public:
    string num;
    long long f[20][181];
    static constexpr int base = 90;

    long long dfs(int pos, int diff, bool lim) {
        if (pos >= (int) num.size()) {
            return diff == 0 ? 1LL : 0LL;
        }
        if (!lim && f[pos][diff + base] != -1) {
            return f[pos][diff + base];
        }
        int up = lim ? num[pos] - '0' : 9;
        long long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, diff + i * (pos % 2 == 0 ? 1 : -1), lim && i == up);
        }
        if (!lim) {
            f[pos][diff + base] = res;
        }
        return res;
    }

    long long countBalanced(long long low, long long high) {
        if (high < 11) {
            return 0;
        }
        low = max(low, 11LL);

        num = to_string(low - 1);
        memset(f, -1, sizeof(f));
        long long a = dfs(0, 0, true);

        num = to_string(high);
        memset(f, -1, sizeof(f));
        long long b = dfs(0, 0, true);

        return b - a;
    }
};
