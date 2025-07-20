const int M = 1e5 + 10;
bool primes[M];
auto init = [] {
    memset(primes, true, sizeof(primes));
    primes[0] = primes[1] = false;
    for (int i = 2; i < M; ++i) {
        if (primes[i]) {
            for (int j = i + i; j < M; j += i) {
                primes[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    long long splitArray(vector<int>& nums) {
        long long ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += primes[i] ? nums[i] : -nums[i];
        }
        return abs(ans);
    }
};