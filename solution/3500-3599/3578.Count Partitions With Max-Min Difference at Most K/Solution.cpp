class Solution {
public:
    int countPartitions(vector<int>& nums, int k) {
        const int mod = 1e9 + 7;
        multiset<int> sl;
        int n = nums.size();
        vector<int> f(n + 1, 0), g(n + 1, 0);
        f[0] = 1;
        g[0] = 1;
        int l = 1;
        for (int r = 1; r <= n; ++r) {
            int x = nums[r - 1];
            sl.insert(x);
            while (*sl.rbegin() - *sl.begin() > k) {
                sl.erase(sl.find(nums[l - 1]));
                ++l;
            }
            f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
            g[r] = (g[r - 1] + f[r]) % mod;
        }
        return f[n];
    }
};
