class Solution {
public:
    int numberOfGoodPartitions(vector<int>& nums) {
        unordered_map<int, int> last;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            last[nums[i]] = i;
        }
        const int mod = 1e9 + 7;
        int j = -1, k = 0;
        for (int i = 0; i < n; ++i) {
            j = max(j, last[nums[i]]);
            k += i == j;
        }
        auto qpow = [&](long long a, int n, int mod) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        return qpow(2, k - 1, mod);
    }
};