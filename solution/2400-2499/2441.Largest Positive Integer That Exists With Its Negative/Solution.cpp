class Solution {
public:
    int findMaxK(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = -1;
        for (int& v : nums) {
            if (s.count(-v)) {
                ans = max(ans, v);
            }
        }
        return ans;
    }
};