class Solution {
    const int MOD = 1337;

public:
    int superPow(int a, vector<int>& b) {
        int ans = 1;
        for (int i = b.size() - 1; i >= 0; --i) {
            ans = (long) ans * quickPowAndMod(a, b[i]) % MOD;
            a = quickPowAndMod(a, 10);
        }
        return ans;
    }

    int quickPowAndMod(int a, int b) {
        int ans = 1;
        while (b) {
            if (b & 1) {
                ans = (ans * (a % MOD)) % MOD;
            }
            b >>= 1;
            a = ((a % MOD) * (a % MOD)) % MOD;
        }
        return ans;
    }
};