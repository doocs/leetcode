class Solution {
public:
    int countKSubsequencesWithMaxBeauty(string s, int k) {
        int f[26]{};
        int cnt = 0;
        for (char& c : s) {
            if (++f[c - 'a'] == 1) {
                ++cnt;
            }
        }
        if (cnt < k) {
            return 0;
        }
        vector<int> vs(cnt);
        for (int i = 0, j = 0; i < 26; ++i) {
            if (f[i]) {
                vs[j++] = f[i];
            }
        }
        sort(vs.rbegin(), vs.rend());
        const int mod = 1e9 + 7;
        long long ans = 1;
        int val = vs[k - 1];
        int x = 0;
        for (int v : vs) {
            x += v == val;
        }
        for (int v : vs) {
            if (v == val) {
                break;
            }
            --k;
            ans = ans * v % mod;
        }
        int c[x + 1][x + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= x; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        auto qpow = [&](long long a, int n) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ans = (ans * c[x][k] % mod) * qpow(val, k) % mod;
        return ans;
    }
};