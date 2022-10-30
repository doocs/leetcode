class Solution {
public:
    int averageValue(vector<int>& nums) {
        int s = 0, n = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                s += v;
                ++n;
            }
        }
        return n == 0 ? 0 : s / n;
    }
};