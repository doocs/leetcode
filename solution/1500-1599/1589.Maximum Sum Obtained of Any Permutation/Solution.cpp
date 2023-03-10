class Solution {
public:
    int maxSumRangeQuery(vector<int>& nums, vector<vector<int>>& requests) {
        int n = nums.size();
        int d[n];
        memset(d, 0, sizeof(d));
        for (auto& req : requests) {
            int l = req[0], r = req[1];
            d[l]++;
            if (r + 1 < n) {
                d[r + 1]--;
            }
        }
        for (int i = 1; i < n; ++i) {
            d[i] += d[i - 1];
        }
        sort(nums.begin(), nums.end());
        sort(d, d + n);
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + 1LL * nums[i] * d[i]) % mod;
        }
        return ans;
    }
};