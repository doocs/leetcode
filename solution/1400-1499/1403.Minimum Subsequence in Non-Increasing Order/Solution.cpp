class Solution {
public:
    vector<int> minSubsequence(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        int s = accumulate(nums.begin(), nums.end(), 0);
        int t = 0;
        vector<int> ans;
        for (int x : nums) {
            t += x;
            ans.push_back(x);
            if (t > s - t) {
                break;
            }
        }
        return ans;
    }
};