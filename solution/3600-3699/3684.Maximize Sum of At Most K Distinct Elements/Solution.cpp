class Solution {
public:
    vector<int> maxKDistinct(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        vector<int> ans;
        for (int i = n - 1; ~i; --i) {
            if (i + 1 < n && nums[i] == nums[i + 1]) {
                continue;
            }
            ans.push_back(nums[i]);
            if (--k == 0) {
                break;
            }
        }
        return ans;
    }
};
