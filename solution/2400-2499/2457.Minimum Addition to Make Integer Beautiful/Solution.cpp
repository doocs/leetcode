using ll = long long;

class Solution {
public:
    long long makeIntegerBeautiful(long long n, int target) {
        auto f = [](ll x) {
            int v = 0;
            while (x) {
                v += x % 10;
                x /= 10;
            }
            return v;
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