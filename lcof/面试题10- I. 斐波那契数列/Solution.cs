public class Solution {
    public int Fib(int n) {
        int a = 0, b = 1, tmp;
        for (int i = 0; i < n; i++) {
            tmp = a;
            a = b;
            b = (tmp + b) % 1000000007;
        }
        return a % 1000000007;
    }
}