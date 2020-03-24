class Solution {
public:
    int waysToStep(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 4, i = 4;
        while (i++ <= n) {
            int t = ((a + b) % 1000000007 + c) % 1000000007;
            a = b;
            b = c;
            c = t;
        }
        return c;
    }
};