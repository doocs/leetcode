class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int s1 = n / 3;
        int m = n % 3;
        if (m == 1) {
            s1 -= 1;
            m = 4;
        }
        long res = 1;
        while (s1-- > 0) {
            res *= 3;
            res %= 1000000007;
        }
        return (int) ((res * (m == 0 ? 1 : m)) % 1000000007);
    }
}