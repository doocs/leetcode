class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        int s = accumulate(nums.begin(), nums.begin() + k, 0);
        int ans = s;
        for (int i = k; i < nums.size(); ++i) {
            s += nums[i] - nums[i - k];
            ans = max(ans, s);
        }
        return static_cast<double>(ans) / k;
    }
};