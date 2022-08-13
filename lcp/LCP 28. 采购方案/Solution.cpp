class Solution {
public:
    int purchasePlans(vector<int>& nums, int target) {
        const int MOD = 1000000007;
        sort(nums.begin(), nums.end());
        int res = 0;
        for (int i = 0, j = nums.size() - 1; i < j; ++i) {
            while (i < j && nums[i] + nums[j] > target) --j;
            if (i < j) res = (res + j - i) % MOD;
        }
        return res % MOD;
    }
};