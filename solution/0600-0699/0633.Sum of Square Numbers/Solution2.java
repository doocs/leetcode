class Solution {
    public boolean judgeSquareSum(int c) {
        int n = (int) Math.sqrt(c);
        for (int i = 2; i <= n; ++i) {
            if (c % i == 0) {
                int exp = 0;
                while (c % i == 0) {
                    c /= i;
                    ++exp;
                }
                if (i % 4 == 3 && exp % 2 != 0) {
                    return false;
                }
            }
        }
        return c % 4 != 3;
    }
}
