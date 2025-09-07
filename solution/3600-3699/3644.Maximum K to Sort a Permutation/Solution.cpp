class Solution {
public:
    int sortPermutation(vector<int>& nums) {
        int ans = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return max(ans, 0);
    }
};
