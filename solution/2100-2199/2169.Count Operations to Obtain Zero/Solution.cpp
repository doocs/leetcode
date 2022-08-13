class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 && num2) {
            if (num1 > num2) swap(num1, num2);
            num2 -= num1;
            ++ans;
        }
        return ans;
    }
};