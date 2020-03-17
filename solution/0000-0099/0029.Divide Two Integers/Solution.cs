public class Solution {
    public int Divide(int dividend, int divisor) {
        return (int)DivideInternal(dividend, divisor);
    }
    
    public long GetHighestBit(long x)
    {
        if (x == 0) return 0;
        long k = 0x80000000;
        while ((x & k) == 0)
        {
            k >>= 1;
        }
        return k;
    }

    public long DivideInternal(long dividend, long divisor)
    {
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        if (dividend < 0) dividend = -dividend;
        if (divisor < 0) divisor = -divisor;
        
        long result = 0;
        long pointer = GetHighestBit(dividend);
        long currentHeader = 1; 
        dividend = dividend ^ pointer;
        while (pointer > 0)
        {
            result <<= 1;
            if (currentHeader >= divisor)
            {
                ++result;
                currentHeader -= divisor;
            }
            pointer >>= 1;
            bool nextIsOne = (dividend & pointer) != 0;
            currentHeader = (currentHeader << 1) + (nextIsOne ? 1 : 0);
            dividend = dividend ^ (nextIsOne ? pointer : 0);
        }
        
        result = sign * result;
        
        if (result > int.MaxValue || result < int.MinValue)
        {
            return int.MaxValue;
        }
        return result;
    }
}