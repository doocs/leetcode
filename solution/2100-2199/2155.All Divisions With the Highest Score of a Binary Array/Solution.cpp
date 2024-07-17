class Solution {
public:
    vector<int> maxScoreIndices(vector<int>& nums) {
        int l0 = 0, r1 = accumulate(nums.begin(), nums.end(), 0);
        int mx = r1;
        vector<int> ans = {0};
        for (int i = 1; i <= nums.size(); ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.push_back(i);
            } else if (mx < t) {
                mx = t;
                ans = {i};
            }
        }
        return ans;
    }
};