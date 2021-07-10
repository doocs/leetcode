class Solution {
public:
    bool judgeSquareSum(int c) {
        long i = 0, j = (long) sqrt(c);
        while (i <= j) {
            long s = i * i + j * j;
            if (s < c) ++i;
            else if (s > c) --j;
            else return true;
        }
        return false;
    }
};