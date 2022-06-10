class Solution {
public:
    int cuttingRope(int n) {
        const int mod = 1000000007;
        if (n < 4) return n - 1;
        long long ans = 1;
        while (n > 4) {
            ans = ans * 3 % mod;
            n -= 3;
        }
        return ans * n % mod;
    }
};
