using LL = long;

class Solution {
public:
    int arrangeCoins(int n) {
        LL left = 1, right = n;
        while (left < right) {
            LL mid = left + right + 1 >> 1;
            LL s = (1 + mid) * mid >> 1;
            if (n < s)
                right = mid - 1;
            else
                left = mid;
        }
        return left;
    }
};