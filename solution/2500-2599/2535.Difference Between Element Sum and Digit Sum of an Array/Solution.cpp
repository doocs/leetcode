class Solution {
public:
    int differenceOfSum(vector<int>& nums) {
        int x = 0, y = 0;
        for (int v : nums) {
            x += v;
            for (; v; v /= 10) {
                y += v % 10;
            }
        }
        return x - y;
    }
};
