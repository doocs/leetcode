class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 1, cnt = 1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
};
