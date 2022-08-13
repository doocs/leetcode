class Solution {
public:
    vector<int> maxScoreIndices(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        int mx = right;
        vector<int> ans;
        ans.push_back(0);
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0)
                ++left;
            else
                --right;
            int t = left + right;
            if (mx == t)
                ans.push_back(i + 1);
            else if (mx < t) {
                mx = t;
                ans.clear();
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};