class Solution {
public:
    int massage(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = g + x;
            int gg = max(f, g);
            f = ff;
            g = gg;
        }
        return max(f, g);
    }
};