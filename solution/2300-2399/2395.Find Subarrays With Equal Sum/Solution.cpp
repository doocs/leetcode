class Solution {
public:
    bool findSubarrays(vector<int>& nums) {
        unordered_set<int> vis;
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i - 1] + nums[i];
            if (vis.count(x)) {
                return true;
            }
            vis.insert(x);
        }
        return false;
    }
};