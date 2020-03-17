using System;

public class Solution {
    public bool CanMeasureWater(int x, int y, int z) {
        if (x == 0 || y == 0) return z == x || z == y;
        var gcd = GetGcd(x, y);
        return z >= 0 && z <= x + y && z % gcd == 0;
    }

    private int GetGcd(int x, int y)
    {
        while (x > 0)
        {
            var quotient = x / y;
            var reminder = x % y;
            if (reminder == 0)
            {
                return y;
            }
            x = y;
            y = reminder;
        }
        throw new Exception("Invalid x or y");
    }
}