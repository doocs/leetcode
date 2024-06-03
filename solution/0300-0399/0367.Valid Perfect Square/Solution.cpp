class Solution {
public:
    bool isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (1LL * mid * mid >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return 1LL * l * l == num;
    }
};