class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool incr = true;
        bool decr = true;
        for (int i = 1; i < nums.size(); ++i)
        {
            if (!incr && !decr) return false;
            if (nums[i] < nums[i - 1]) incr = false;
            if (nums[i] > nums[i - 1]) decr = false;
        }
        return incr || decr;
    }
};