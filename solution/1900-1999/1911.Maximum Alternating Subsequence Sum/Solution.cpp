class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        long long f = 0, g = 0;
        for (int& x : nums) {
            long ff = max(g - x, f), gg = max(f + x, g);
            f = ff, g = gg;
        }
        return max(f, g);
    }
};