class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int n = nums.size();
        int k = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[k] < nums[i]) {
                k = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (k != i && nums[k] < nums[i] * 2) {
                return -1;
            }
        }
        return k;
    }
};