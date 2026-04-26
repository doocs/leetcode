class Solution {
public:
    bool validDigit(int n, int x) {
        bool hasX = false;
        while (n > 9) {
            hasX = hasX || (n % 10 == x);
            n /= 10;
        }
        return hasX && (n != x);
    }
};
