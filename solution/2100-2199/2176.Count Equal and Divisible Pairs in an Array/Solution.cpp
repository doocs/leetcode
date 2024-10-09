class Solution {
public:
    int countPairs(vector<int>& nums, int k) {
        int ans = 0;
        for (int j = 1; j < nums.size(); ++j) {
            for (int i = 0; i < j; ++i) {
                ans += nums[i] == nums[j] && (i * j % k) == 0;
            }
        }
        return ans;
    }
};
