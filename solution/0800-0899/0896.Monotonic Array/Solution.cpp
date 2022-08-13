class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool isIncr = false;
        bool isDecr = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] < nums[i - 1]) isIncr = true;
            if (nums[i] > nums[i - 1]) isDecr = true;
            if (isIncr && isDecr) return false;
        }
        return true;
    }
};