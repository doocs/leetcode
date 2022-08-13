using ll = long long;
const int MOD = 1e9 + 7;

class Solution {
public:
    int numPrimeArrangements(int n) {
        int cnt = count(n);
        ll ans = f(cnt) * f(n - cnt);
        return (int)(ans % MOD);
    }

    ll f(int n) {
        ll ans = 1;
        for (int i = 2; i <= n; ++i) ans = (ans * i) % MOD;
        return ans;
    }

    int count(int n) {
        vector<bool> primes(n + 1, true);
        int cnt = 0;
        for (int i = 2; i <= n; ++i) {
            if (primes[i]) {
                ++cnt;
                for (int j = i + i; j <= n; j += i) primes[j] = false;
            }
        }
        return cnt;
    }
};