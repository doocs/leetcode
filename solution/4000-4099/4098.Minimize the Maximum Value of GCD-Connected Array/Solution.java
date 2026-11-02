class Solution {
    public long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long p = value;
        for (long x = 2; x * x <= value; x++) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long) value - p - ((long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long) value + p + ((long) n - 2 - index) * 2;
    }
}