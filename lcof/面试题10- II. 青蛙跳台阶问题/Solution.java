class Solution {
    public int numWays(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return b;
    }
}