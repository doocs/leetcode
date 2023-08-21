class Solution {
public:
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1 << 30;
        for (int x : nums) {
            int y = abs(x);
            if (y < d || (y == d && x > ans)) {
                ans = x;
                d = y;
            }
        }
        return ans;
    }
};