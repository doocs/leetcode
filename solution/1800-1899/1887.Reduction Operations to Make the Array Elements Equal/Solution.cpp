class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.size(); ++i) {
            cnt += nums[i] != nums[i - 1];
            ans += cnt;
        }
        return ans;
    }
};
