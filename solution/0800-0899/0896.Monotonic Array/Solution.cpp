class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool asc = false, desc = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                asc = true;
            } else if (nums[i - 1] > nums[i]) {
                desc = true;
            }
            if (asc && desc) {
                return false;
            }
        }
        return true;
    }
};