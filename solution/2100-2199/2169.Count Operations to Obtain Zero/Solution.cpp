class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        for (; num1 && num2; ++ans) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        return ans;
    }
};
