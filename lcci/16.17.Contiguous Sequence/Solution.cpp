class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int ans = INT_MIN, f = INT_MIN;
        for (int x : nums) {
            f = max(f, 0) + x;
            ans = max(ans, f);
        }
        return ans;
    }
};