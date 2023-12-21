class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        int m = *max_element(begin(nums), end(nums));
        int cnt[m + 1];
        memset(cnt, 0, sizeof(cnt));
        int n = nums.size();
        int v = 0;
        vector<int> ans(n - k + 1);
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
};