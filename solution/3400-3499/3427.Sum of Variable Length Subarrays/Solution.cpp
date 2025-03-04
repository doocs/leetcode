class Solution {
public:
    int subarraySum(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += s[i + 1] - s[max(0, i - nums[i])];
        }
        return ans;
    }
};
