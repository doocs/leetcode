class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        for (int x : nums) {
            k ^= x;
        }
        return __builtin_popcount(k);
    }
};