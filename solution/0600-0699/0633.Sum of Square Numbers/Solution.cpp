class Solution {
public:
    bool judgeSquareSum(int c) {
        long a = 0, b = (long) sqrt(c);
        while (a <= b) {
            long s = a * a + b * b;
            if (s == c) return true;
            if (s < c)
                ++a;
            else
                --b;
        }
        return false;
    }
};