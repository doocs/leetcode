class Solution {
public:
    int differenceOfSum(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a += x;
            for (; x; x /= 10) {
                b += x % 10;
            }
        }
        return abs(a - b);
    }
};