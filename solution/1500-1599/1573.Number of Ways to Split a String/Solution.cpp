class Solution {
public:
    int numWays(string s) {
        int cnt = 0;
        for (char& c : s) {
            cnt += c == '1';
        }
        int m = cnt % 3;
        if (m) {
            return 0;
        }
        const int mod = 1e9 + 7;
        int n = s.size();
        if (cnt == 0) {
            return (n - 1LL) * (n - 2) / 2 % mod;
        }
        cnt /= 3;
        auto find = [&](int x) {
            int t = 0;
            for (int i = 0;; ++i) {
                t += s[i] == '1';
                if (t == x) {
                    return i;
                }
            }
        };
        int i1 = find(cnt), i2 = find(cnt + 1);
        int j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (1LL * (i2 - i1) * (j2 - j1)) % mod;
    }
};