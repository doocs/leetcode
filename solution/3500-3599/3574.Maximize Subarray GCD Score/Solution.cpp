class Solution {
public:
    long long maxGCDScore(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> cnt(n);
        for (int i = 0; i < n; ++i) {
            for (int x = nums[i]; x % 2 == 0; x /= 2) {
                ++cnt[i];
            }
        }

        long long ans = 0;
        for (int l = 0; l < n; ++l) {
            int g = 0;
            int mi = INT32_MAX;
            int t = 0;
            for (int r = l; r < n; ++r) {
                g = gcd(g, nums[r]);
                if (cnt[r] < mi) {
                    mi = cnt[r];
                    t = 1;
                } else if (cnt[r] == mi) {
                    ++t;
                }
                long long score = static_cast<long long>(r - l + 1) * (t > k ? g : g * 2);
                ans = max(ans, score);
            }
        }

        return ans;
    }
};
