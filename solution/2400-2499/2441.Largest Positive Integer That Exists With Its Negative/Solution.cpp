class Solution {
public:
    int findMaxK(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = -1;
        for (int x : s) {
            if (s.count(-x)) {
                ans = max(ans, x);
            }
        }
        return ans;
    }
};