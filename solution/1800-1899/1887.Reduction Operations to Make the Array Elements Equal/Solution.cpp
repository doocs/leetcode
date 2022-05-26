class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int cnt = 0, res = 0, n = nums.size();
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) ++cnt;
            res += cnt;
        }
        return res;
    }
};