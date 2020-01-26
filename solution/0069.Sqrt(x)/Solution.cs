public class Solution {
    public int MySqrt(int x) {
        long l = 0;
        long r = x;
        while (l < r)
        {
            var mid = (l + r) / 2;
            if (mid * mid <= x)
            {
                l = mid;
                if (l == mid)
                {
                    if (r * r <= x)
                    {
                        l = r;
                    }
                    else
                    {
                        --r;
                    }
                }
            }
            else
            {
                r = mid;
            }
        }
        return (int) l;
    }
}