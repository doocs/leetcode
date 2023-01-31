class Solution {
public:
    int numWays(int n) {
        int a = 1, b = 1;
        while (n--) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return a;
    }
};