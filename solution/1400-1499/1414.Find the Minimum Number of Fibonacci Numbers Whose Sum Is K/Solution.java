class Solution {
    public int findMinFibonacciNumbers(int k) {
        if (k < 2) {
            return k;
        }
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}