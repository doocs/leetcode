class Solution {
public:
    int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (x / mid >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};