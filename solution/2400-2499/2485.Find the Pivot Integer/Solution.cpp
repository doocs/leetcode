class Solution {
public:
    int pivotInteger(int n) {
        for (int x = 1; x < 1000; ++x) {
            if ((1 + x) * x == (x + n) * (n - x + 1)) {
                return x;
            }
        }
        return -1;
    }
};