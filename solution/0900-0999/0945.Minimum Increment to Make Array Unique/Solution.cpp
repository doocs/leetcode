class Solution {
public:
    int minIncrementForUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, y = -1;
        for (int x : nums) {
            y = max(y + 1, x);
            ans += y - x;
        }
        return ans;
    }
};