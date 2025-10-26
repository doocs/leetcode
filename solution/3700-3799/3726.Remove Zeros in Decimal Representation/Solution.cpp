class Solution {
public:
    long long removeZeros(long long n) {
        long long k = 1;
        long long ans = 0;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                ans = k * x + ans;
                k *= 10;
            }
            n /= 10;
        }
        return ans;
    }
};
