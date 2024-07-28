const int m = 31623;
bool primes[m + 1];

auto init = [] {
    memset(primes, true, sizeof(primes));
    primes[0] = primes[1] = false;
    for (int i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (int j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    int nonSpecialCount(int l, int r) {
        int lo = ceil(sqrt(l));
        int hi = floor(sqrt(r));
        int cnt = 0;
        for (int i = lo; i <= hi; ++i) {
            if (primes[i]) {
                ++cnt;
            }
        }
        return r - l + 1 - cnt;
    }
};