class Solution {
public:
    vector<vector<int>> findPrimePairs(int n) {
        bool primes[n];
        memset(primes, true, sizeof(primes));
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        vector<vector<int>> ans;
        for (int x = 2; x <= n / 2; ++x) {
            int y = n - x;
            if (primes[x] && primes[y]) {
                ans.push_back({x, y});
            }
        }
        return ans;
    }
};