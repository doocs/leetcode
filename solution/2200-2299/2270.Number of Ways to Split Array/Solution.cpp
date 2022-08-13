class Solution {
public:
    int waysToSplitArray(vector<int>& nums) {
        long long s = accumulate(nums.begin(), nums.end(), 0ll);
        long long t = 0;
        int ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            t += nums[i];
            ans += t >= s - t;
        }
        return ans;
    }
};