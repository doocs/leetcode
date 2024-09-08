class Solution {
public:
    long long findMaximumScore(vector<int>& nums) {
        long long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = max(mx, nums[i]);
            ans += mx;
        }
        return ans;
    }
};
