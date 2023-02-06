class Solution {
public:
    int closestFair(int n) {
        int a = 0, b = 0;
        int t = n, k = 0;
        while (t) {
            if ((t % 10) & 1) {
                ++a;
            } else {
                ++b;
            }
            ++k;
            t /= 10;
        }
        if (a == b) {
            return n;
        }
        if (k % 2 == 1) {
            int x = pow(10, k);
            int y = 0;
            for (int i = 0; i<k> > 1; ++i) {
                y = y * 10 + 1;
            }
            return x + y;
        }
        return closestFair(n + 1);
    }
};