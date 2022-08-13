class Solution {
public:
    vector<int> minSubsequence(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int s = 0;
        for (int v : nums) s += v;
        int t = 0;
        vector<int> ans;
        for (int i = nums.size() - 1; ~i; --i) {
            t += nums[i];
            ans.push_back(nums[i]);
            if (t > s - t) break;
        }
        return ans;
    }
};