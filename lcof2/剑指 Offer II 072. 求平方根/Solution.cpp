class Solution {
public:
    int mySqrt(int x) {
        long long left = 0, right = x;
        while (left < right) {
            long long mid = left + ((right - left + 1) >> 1);
            if (mid <= x / mid)
                left = mid;
            else
                right = mid - 1;
        }
        return (int)left;
    }
};