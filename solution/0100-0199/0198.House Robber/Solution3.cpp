class Solution {
public:
    int rob(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = max(f, g);
            g = f + x;
            f = ff;
        }
        return max(f, g);
    }
};
