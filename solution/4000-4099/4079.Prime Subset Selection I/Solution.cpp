class Solution {
    static const int mx = 1000000;
    static vector<int> primes;

public:
    vector<int> maxPrimes(int n, int s) {
        vector<int> ans;
        for (int p : primes) {
            if (p > n || p > s) {
                break;
            }
            ans.push_back(p);
            s -= p;
        }
        return ans;
    }
};

const int Solution::mx;
vector<int> Solution::primes = [] {
    vector<bool> isPrime(mx + 1, true);
    isPrime[0] = isPrime[1] = false;
    vector<int> primes;

    for (int i = 2; i <= mx; i++) {
        if (isPrime[i]) {
            primes.push_back(i);
            if (i <= mx / i) {
                for (int j = i * i; j <= mx; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    return primes;
}();