class Solution {
public:
    bool canDivideIntoSubsequences(vector<int>& nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int& b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.size()) {
                return false;
            }
            a = b;
        }
        return true;
    }
};