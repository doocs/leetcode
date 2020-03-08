class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n == -1)
            return 1 / x;
        double half = myPow(x, n / 2);
        return half * half * myPow(x, n % 2);
    }
}