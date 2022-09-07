class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        int cnt[100010] = {0};
        int x = 0;
        for (int i = 0; i < k; ++i) {
            if (cnt[nums[i]]++ == 0) {
                ++x;
            }
        }
        int n = nums.size();
        vector<int> ans(n - k + 1);
        ans[0] = x;
        for (int i = k; i < n; ++i) {
            if (--cnt[nums[i - k]] == 0) {
                --x;
            }
            if (cnt[nums[i]]++ == 0) {
                ++x;
            }
            ans[i - k + 1] = x;
        }
        return ans;
    }
};