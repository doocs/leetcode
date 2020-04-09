class Solution {
    public int findNthDigit(int n) {
        if (n < 10) return n;
        int pow = 0, count;
        while (true) {
            count = getBitNum(pow);
            if (n < count) break;
            n -= count;
            ++pow;
        }
        int num = n / (pow + 1) + (int) Math.pow(10, pow);
        return String.valueOf(num).charAt(n % (pow + 1)) - '0';
    }

    private int getBitNum(int pow) {
        if (pow == 0) {
            return 10;
        }
        return (int) (9 * Math.pow(10, pow) * (pow + 1));
    }
}