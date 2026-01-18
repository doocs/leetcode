class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& target) {
        unordered_set<int> s;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] != target[i]) {
                s.insert(nums[i]);
            }
        }
        return s.size();
    }
};
