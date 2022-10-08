class Solution {
public:
    vector<int> mostCompetitive(vector<int>& nums, int k) {
        vector<int> stk;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (stk.size() && stk.back() > nums[i] && stk.size() + n - i > k) {
                stk.pop_back();
            }
            if (stk.size() < k) {
                stk.push_back(nums[i]);
            }
        }
        return stk;
    }
};