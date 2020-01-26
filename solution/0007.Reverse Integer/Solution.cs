public class Solution {
    public int Reverse(int x) {
        var negative = x < 0;
        if (negative) x = -x;
        long result = 0;
        while (x > 0)
        {
            result = (result * 10) + x % 10;
            x /= 10;
        }
        if (negative) result = -result;
        if (result > int.MaxValue || result < int.MinValue) result = 0;
        return (int) result;
    }
}