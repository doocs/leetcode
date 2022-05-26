class Solution {
    public double myPow(double x, int n) {
        if(n < 0) return sum(1.0 / x,0 - n);
        return sum(x,n);
    }

    public double sum(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        if( n % 2 == 0) return sum(x * x, n / 2);
        else return x * sum(x * x, n / 2);
    }
}