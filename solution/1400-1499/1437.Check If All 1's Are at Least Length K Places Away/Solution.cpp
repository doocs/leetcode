class Solution {
public:
    bool kLengthApart(vector<int>& nums, int k) {
        int j = -(k + 1);
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 1) {
                if (i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
};