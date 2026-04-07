class Solution {
public:
    long long countFancy(long long l, long long r) {
        auto check = [&](int s) -> bool {
            if (s < 100) {
                return s % 11 != 0;
            }
            int mid = (s / 10) % 10;
            int last = s % 10;
            return mid > 1 && mid < last;
        };

        string num = to_string(l - 1);
        int n = num.size();
        vector f(n, vector(9 * n + 1, vector(10, vector<long long>(4, -1))));

        auto dfs = [&](this auto&& dfs, int pos, int s, int prev, int st, bool lim) -> long long {
            if (pos >= n) {
                if (st != 3) return 1;
                return check(s) ? 1LL : 0LL;
            }

            if (!lim && f[pos][s][prev][st] != -1) {
                return f[pos][s][prev][st];
            }

            int up = lim ? num[pos] - '0' : 9;
            long long res = 0;

            for (int i = 0; i <= up; i++) {
                int nxtSt = st;

                if (st == 0) {
                    if (prev == 0)
                        nxtSt = 0;
                    else if (i > prev)
                        nxtSt = 1;
                    else if (i < prev)
                        nxtSt = 2;
                    else
                        nxtSt = 3;
                } else if (st == 1) {
                    if (i > prev)
                        nxtSt = 1;
                    else
                        nxtSt = 3;
                } else if (st == 2) {
                    if (i < prev)
                        nxtSt = 2;
                    else
                        nxtSt = 3;
                } else {
                    nxtSt = 3;
                }

                res += dfs(pos + 1, s + i, i, nxtSt, lim && i == up);
            }

            if (!lim) {
                f[pos][s][prev][st] = res;
            }

            return res;
        };

        long long a = dfs(0, 0, 0, 0, true);

        num = to_string(r);
        n = num.size();
        f.assign(n, vector(9 * n + 1, vector(10, vector<long long>(4, -1))));

        long long b = dfs(0, 0, 0, 0, true);

        return b - a;
    }
};
