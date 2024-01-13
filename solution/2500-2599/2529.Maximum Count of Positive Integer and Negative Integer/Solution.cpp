class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = 0, b = 0;
        for (int& v : nums) {
            if (v > 0) {
                ++a;
            }
            if (v < 0) {
                ++b;
            }
        }
        return max(a, b);
    }
};