class Solution {
public:
    int maxNumOfMarkedIndices(vector<int>& nums) {
        ranges::sort(nums);
        int i = 0, n = nums.size();
        for (int j = (n + 1) / 2; j < n; ++j) {
            if (nums[i] * 2 <= nums[j]) {
                ++i;
            }
        }
        return i * 2;
    }
};
