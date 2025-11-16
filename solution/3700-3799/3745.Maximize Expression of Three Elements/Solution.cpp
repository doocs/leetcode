class Solution {
public:
    int maximizeExpressionOfThree(vector<int>& nums) {
        const int inf = 1 << 30;
        int a = -inf, b = -inf, c = inf;
        for (int x : nums) {
            if (x < c) {
                c = x;
            }
            if (x >= a) {
                b = a;
                a = x;
            } else if (x > b) {
                b = x;
            }
        }
        return a + b - c;
    }
};
