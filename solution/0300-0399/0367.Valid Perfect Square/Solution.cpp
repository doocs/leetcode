class Solution {
public:
    bool isPerfectSquare(int num) {
        long left = 1, right = num;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (num / mid <= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left * left == num;
    }
};