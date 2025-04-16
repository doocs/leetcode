class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        return reduce(nums.begin(), nums.end(), 0) % k;
    }
};