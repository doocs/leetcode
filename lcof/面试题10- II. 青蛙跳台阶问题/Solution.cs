public class Solution {
    public int NumWays(int n) {
        int a = 1, b = 1, tmp;
        for (int i = 0; i < n; i++) {
            tmp = a;
            a = b;
            b = (tmp + b) % 1000000007;
        }
        return a % 1000000007;
    }
}