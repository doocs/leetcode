class Solution {
public:
    long long countGoodIntegersOnPath(long long l, long long r, string directions) {
        bool key[16];
        memset(key, 0, sizeof(key));
        int row = 0, col = 0;
        key[0] = true;
        for (char c : directions) {
            if (c == 'D') {
                row++;
            } else {
                col++;
            }
            key[row * 4 + col] = true;
        }

        long long f[16][10];
        string s;

        auto dfs = [&](this auto&& dfs, int pos, int last, bool lim) -> long long {
            if (pos == 16) {
                return 1;
            }
            if (!lim && f[pos][last] != -1) {
                return f[pos][last];
            }

            long long res = 0;
            int start = key[pos] ? last : 0;
            int end = lim ? (s[pos] - '0') : 9;

            for (int i = start; i <= end; i++) {
                res += dfs(pos + 1, key[pos] ? i : last, lim && (i == end));
            }

            if (!lim) {
                f[pos][last] = res;
            }
            return res;
        };

        auto calc = [&](long long x) {
            if (x < 0) {
                return 0LL;
            }
            string t = to_string(x);
            s = string(16 - t.length(), '0') + t;
            memset(f, -1, sizeof(f));
            return dfs(0, 0, true);
        };

        return calc(r) - calc(l - 1);
    }
};
