class Solution {
    public int findNthDigit(int n) {
        int bits = 1, t = 9;
        while (n / bits > t) {
            n -= bits * t;
            ++bits;
            t *= 10;
        }
        int start = (int) Math.pow(10, bits - 1) + (n / bits) - 1;
        if (n % bits == 0) {
            return start % 10;
        }
        return String.valueOf(start + 1).charAt((n % bits) - 1) - '0';
    }
}