class Solution {
public:
    int minCost(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            vector<int> cnt(n);
            int one = 0;
            long long ans = 1LL << 60;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = min(ans, (long long) k + j - i + 1 - one + f[j + 1]);
            }
            f[i] = (int) ans;
        }
        return f[0];
    }
};
