static const int MX = 500000;
static vector<bool> IS_PRIME(MX + 1, true);
static vector<int> PRIMES;
static vector<int> S;

auto init = [] {
    IS_PRIME[0] = false;
    IS_PRIME[1] = false;

    for (int i = 2; i <= MX; i++) {
        if (IS_PRIME[i]) {
            PRIMES.push_back(i);
            if (1LL * i * i <= MX) {
                for (int j = i * i; j <= MX; j += i) {
                    IS_PRIME[j] = false;
                }
            }
        }
    }

    S.push_back(0);
    int t = 0;
    for (int x : PRIMES) {
        t += x;
        if (t > MX) break;
        if (IS_PRIME[t]) {
            S.push_back(t);
        }
    }

    return 0;
}();

class Solution {
public:
    int largestPrime(int n) {
        auto it = upper_bound(S.begin(), S.end(), n);
        return *(it - 1);
    }
};
