class Solution {
    string s;
    long long f[20][2][2];

    long long dfs(int pos, int st, bool lim) {
        if (pos >= s.size()) {
            return st ^ 1;
        }
        if (f[pos][st][lim] != -1) {
            return f[pos][st][lim];
        }
        int up = lim ? s[pos] - '0' : 9;
        long long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim && i == up);
        }
        return f[pos][st][lim] = res;
    }

    long long calc(long long x) {
        s = to_string(x);
        memset(f, -1, sizeof(f));
        return dfs(0, 0, true);
    }

public:
    long long countEvenlyGoodIntegers(long long l, long long r) {
        return calc(r) - calc(l - 1);
    }
};