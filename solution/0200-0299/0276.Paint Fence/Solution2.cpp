class Solution {
public:
    int numWays(int n, int k) {
        int f = k, g = 0;
        for (int i = 1; i < n; ++i) {
            int ff = (f + g) * (k - 1);
            g = f;
            f = ff;
        }
        return f + g;
    }
};