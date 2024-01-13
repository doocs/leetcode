class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        unordered_set<int> vis;
        for (int i = 0;; ++i) {
            if (vis.count(nums[i])) {
                return nums[i];
            }
            vis.insert(nums[i]);
        }
    }
};