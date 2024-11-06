class Solution {
public:
    int waysToSplitArray(vector<int>& nums) {
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        long long t = 0;
        int ans = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            t += nums[i];
            ans += t >= s - t;
        }
        return ans;
    }
};
