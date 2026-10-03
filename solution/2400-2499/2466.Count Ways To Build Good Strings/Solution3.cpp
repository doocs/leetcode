class Solution {
public:
    const int mod = 1e9 + 7;

    int countGoodStrings(int low, int high, int zero, int one) {
        vector<int> f(high + 1);
        for (int i = high; i >= 0; --i) {
            long ans = i >= low && i <= high;
            if (i + zero <= high) {
                ans += f[i + zero];
            }
            if (i + one <= high) {
                ans += f[i + one];
            }
            f[i] = ans % mod;
        }
        return f[0];
    }
};
