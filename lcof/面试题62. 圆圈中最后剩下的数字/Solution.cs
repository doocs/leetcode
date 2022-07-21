public class Solution {
    public int LastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i < n + 1; i++) {
            f = (f + m) % i;
        }
        return f;
    }
}