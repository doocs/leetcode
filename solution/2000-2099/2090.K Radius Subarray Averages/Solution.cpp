class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        k = k << 1 | 1;
        int n = nums.size();
        vector<int> ans(n, -1);
        if (k > n) {
            return ans;
        }
        long long s = accumulate(nums.begin(), nums.begin() + k, 0LL);
        int j = k / 2;
        ans[j] = s / k;
        for (int i = k; i < n; ++i) {
            s += nums[i] - nums[i - k];
            ans[++j] = s / k;
        }
        return ans;
    }
};