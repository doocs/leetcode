#include <ranges>

class Solution {
public:
    vector<int> maxSubsequence(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> idx(n);
        ranges::iota(idx, 0);
        ranges::sort(idx, [&](int i, int j) { return nums[i] < nums[j]; });
        ranges::sort(idx | views::drop(n - k));
        vector<int> ans(k);
        for (int i = n - k; i < n; ++i) {
            ans[i - (n - k)] = nums[idx[i]];
        }
        return ans;
    }
};