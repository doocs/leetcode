class Solution {
    public int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        if (n < 4) {
            return n - 1;
        }

        int timesOf3 = n / 3;
        if (n % 3 == 1) {
            --timesOf3;
        }
        int timesOf2 = (n - timesOf3 * 3) >> 1;
        return (int) (Math.pow(2, timesOf2) * Math.pow(3, timesOf3));
    }
}