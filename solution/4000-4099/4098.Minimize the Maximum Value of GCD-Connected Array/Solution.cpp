class Solution {
public:
    long long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long long p = value;
        for (long long x = 2; x * x <= value; ++x) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long long) value - p - ((long long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long long) value + p + ((long long) n - 2 - index) * 2;
    }
};