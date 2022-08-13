class Solution {
public:
    bool isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left < right) {
            long mid = left + right >> 1;
            if (mid * mid >= num)
                right = mid;
            else
                left = mid + 1;
        }
        return left * left == num;
    }
};