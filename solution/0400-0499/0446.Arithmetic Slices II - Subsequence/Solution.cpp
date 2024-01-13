class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        unordered_map<long long, int> f[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long long d = 1LL * nums[i] - nums[j];
                int cnt = f[j][d];
                ans += cnt;
                f[i][d] += cnt + 1;
            }
        }
        return ans;
    }
};