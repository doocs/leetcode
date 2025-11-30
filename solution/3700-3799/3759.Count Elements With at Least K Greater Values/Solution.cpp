class Solution {
public:
    int countElements(vector<int>& nums, int k) {
        int n = nums.size();
        if (k == 0) {
            return n;
        }
        ranges::sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
};
