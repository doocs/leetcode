class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int cnt[201]{};
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
};