class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int s = INT_MIN, ans = INT_MIN;
        for (int v : nums) {
            s = max(s, 0) + v;
            ans = max(ans, s);
        }
        return ans;
    }
};