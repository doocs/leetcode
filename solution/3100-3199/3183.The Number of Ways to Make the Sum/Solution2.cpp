const int m = 1e5 + 1;
const int mod = 1e9 + 7;
int f[m + 1];

auto init = [] {
    f[0] = 1;
    int coins[3] = {1, 2, 6};
    for (int x : coins) {
        for (int j = x; j < m; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
    return 0;
}();


class Solution {
public:
    int numberOfWays(int n) {
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % mod;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % mod;
        }
        return ans;
    }
};