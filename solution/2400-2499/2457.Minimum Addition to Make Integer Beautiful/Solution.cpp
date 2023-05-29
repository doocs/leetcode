class Solution {
public:
    long long makeIntegerBeautiful(long long n, int target) {
        using ll = long long;
        auto f = [](ll x) {
            int y = 0;
            while (x) {
                y += x % 10;
                x /= 10;
            }
            return y;
        };

        ll x = 0;
        while (f(n + x) > target) {
            ll y = n + x;
            ll p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }
};