class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = nums.size() - 1; ~i; --i) {
            if (s.contains(nums[i])) {
                return i / 3 + 1;
            }
            s.insert(nums[i]);
        }
        return 0;
    }
};
