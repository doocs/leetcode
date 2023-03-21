class Solution {
public:
    vector<int> leftRigthDifference(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        vector<int> ans;
        for (int& x : nums) {
            right -= x;
            ans.push_back(abs(left - right));
            left += x;
        }
        return ans;
    }
};