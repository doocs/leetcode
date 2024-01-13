class Solution {
    private int n;

    public int sumOfMultiples(int n) {
        this.n = n;
        return f(3) + f(5) + f(7) - f(3 * 5) - f(3 * 7) - f(5 * 7) + f(3 * 5 * 7);
    }

    private int f(int x) {
        int m = n / x;
        return (x + m * x) * m / 2;
    }
}