class Solution {
public:
    int semiOrderedPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), 1) - nums.begin();
        int j = find(nums.begin(), nums.end(), n) - nums.begin();
        int k = i < j ? 1 : 2;
        return i + n - j - k;
    }
};