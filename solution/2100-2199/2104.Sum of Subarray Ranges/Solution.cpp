class Solution {
public:
    long long subArrayRanges(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int i = 0; i < n - 1; ++i) {
            int mi = nums[i], mx = nums[i];
            for (int j = i + 1; j < n; ++j) {
                mi = min(mi, nums[j]);
                mx = max(mx, nums[j]);
                ans += (mx - mi);
            }
        }
        return ans;
    }
};