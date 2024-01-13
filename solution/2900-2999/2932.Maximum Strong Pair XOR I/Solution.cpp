class Solution {
public:
    int maximumStrongPairXor(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            for (int y : nums) {
                if (abs(x - y) <= min(x, y)) {
                    ans = max(ans, x ^ y);
                }
            }
        }
        return ans;
    }
};