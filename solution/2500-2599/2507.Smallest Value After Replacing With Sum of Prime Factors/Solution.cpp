class Solution {
public:
    int smallestValue(int n) {
        while (1) {
            int t = n, s = 0;
            for (int i = 2; i <= n / i; ++i) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
            if (n > 1) s += n;
            if (s == t) return s;
            n = s;
        }
    }
};