class Solution {
public:
    int returnToBoundaryCount(vector<int>& nums) {
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x;
            ans += s == 0;
        }
        return ans;
    }
};