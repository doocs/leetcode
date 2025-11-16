class Solution {
public:
    long long countDistinct(long long n) {
        string s = to_string(n);
        int m = s.size();
        static long long f[20][2][2][2];
        memset(f, -1, sizeof(f));

        auto dfs = [&](this auto&& dfs, int i, int zero, int lead, int limit) -> long long {
            if (i == m) {
                return (zero == 0 && lead == 0) ? 1 : 0;
            }
            if (!limit && f[i][zero][lead][limit] != -1) {
                return f[i][zero][lead][limit];
            }

            int up = limit ? (s[i] - '0') : 9;
            long long ans = 0;
            for (int d = 0; d <= up; d++) {
                int nxtZero = zero || (d == 0 && !lead);
                int nxtLead = lead && d == 0;
                int nxtLimit = limit && d == up;
                ans += dfs(i + 1, nxtZero, nxtLead, nxtLimit);
            }

            if (!limit) f[i][zero][lead][limit] = ans;
            return ans;
        };

        return dfs(0, 0, 1, 1);
    }
};
