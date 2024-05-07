class Solution {
public:
    long long minEnd(int n, int x) {
        --n;
        long long ans = x;
        for (int i = 0; i < 31; ++i) {
            if (x >> i & 1 ^ 1) {
                ans |= (n & 1) << i;
                n >>= 1;
            }
        }
        ans |= (1LL * n) << 31;
        return ans;
    }
};