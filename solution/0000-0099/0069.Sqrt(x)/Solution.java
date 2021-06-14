class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int low = 1, high = x;
        while (low < high) {
            int mid = (low + high + 1) >>> 1;
            if (x / mid >= mid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}