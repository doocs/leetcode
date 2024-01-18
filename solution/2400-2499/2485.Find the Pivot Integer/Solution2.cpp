class Solution {
public:
    int pivotInteger(int n) {
        int y = n * (n + 1) / 2;
        int x = sqrt(y);
        return x * x == y ? x : -1;
    }
};