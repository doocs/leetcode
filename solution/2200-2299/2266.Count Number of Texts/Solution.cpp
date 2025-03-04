const int mod = 1e9 + 7;
const int n = 1e5 + 10;
long long f[n], g[n];

int init = []() {
    f[0] = g[0] = 1;
    f[1] = g[1] = 1;
    f[2] = g[2] = 2;
    f[3] = g[3] = 4;
    for (int i = 4; i < n; ++i) {
        f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % mod;
        g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % mod;
    }
    return 0;
}();

class Solution {
public:
    int countTexts(string pressedKeys) {
        long long ans = 1;
        for (int i = 0, n = pressedKeys.length(); i < n; ++i) {
            char c = pressedKeys[i];
            int j = i;
            while (j + 1 < n && pressedKeys[j + 1] == c) {
                ++j;
            }
            int cnt = j - i + 1;
            ans = c == '7' || c == '9' ? ans * g[cnt] : ans * f[cnt];
            ans %= mod;
            i = j;
        }
        return ans;
    }
};
