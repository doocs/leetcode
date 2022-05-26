public class Solution {
    public int MySqrt(int x) {
        int left = 0, right = x;
        while (left < right)
        {
            int mid = left + right + 1 >> 1;
            if (mid <= x / mid)
            {
                left = mid;
            }
            else
            {
                right = mid - 1;
            }
        }
        return left;
    }
}