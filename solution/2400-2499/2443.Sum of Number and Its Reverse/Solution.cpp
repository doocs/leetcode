class Solution {
public:
    bool sumOfNumberAndReverse(int num) {
        for (int x = 0; x <= num; ++x) {
            int k = x;
            int y = 0;
            while (k > 0) {
                y = y * 10 + k % 10;
                k /= 10;
            }
            if (x + y == num) {
                return true;
            }
        }
        return false;
    }
};