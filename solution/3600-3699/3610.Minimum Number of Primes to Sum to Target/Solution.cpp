class Solution {
public:
    int minNumberOfPrimes(int n, int m) {
        static vector<int> primes;
        if (primes.empty()) {
            int x = 2;
            int M = 1000;
            while ((int) primes.size() < M) {
                bool is_prime = true;
                for (int p : primes) {
                    if (p * p > x) break;
                    if (x % p == 0) {
                        is_prime = false;
                        break;
                    }
                }
                if (is_prime) primes.push_back(x);
                x++;
            }
        }

        vector<int> f(n + 1, INT_MAX);
        f[0] = 0;
        for (int x : vector<int>(primes.begin(), primes.begin() + m)) {
            for (int i = x; i <= n; ++i) {
                if (f[i - x] != INT_MAX) {
                    f[i] = min(f[i], f[i - x] + 1);
                }
            }
        }
        return f[n] < INT_MAX ? f[n] : -1;
    }
};
