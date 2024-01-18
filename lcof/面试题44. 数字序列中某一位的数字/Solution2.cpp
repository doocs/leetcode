class Solution {
public:
    int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        n -= 10;
        int k = 2, p = 10;
        while (n >= 9ll * k * p) {
            n -= 9 * k * p;
            ++k;
            p *= 10;
        }
        int x = p + n / k;
        return to_string(x)[n % k] - '0';
    }
};