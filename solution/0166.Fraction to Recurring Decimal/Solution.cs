// https://leetcode.com/problems/fraction-to-recurring-decimal/

using System.Collections.Generic;
using System.Text;

public partial class Solution
{
    public string FractionToDecimal(int numerator, int denominator)
    {
        var n = (long)numerator;
        var d = (long)denominator;
        var sb = new StringBuilder();
        if (n < 0)
        {
            n = -n;
            if (d < 0)
            {
                d = -d;
            }
            else
            {
                sb.Append('-');
            }
        }
        else if (n > 0 && d < 0)
        {
            d = -d;
            sb.Append('-');
        }

        sb.Append(n / d);
        n = n % d;
        if (n != 0)
        {
            sb.Append('.');
            var dict = new Dictionary<long, int>();
            while (n != 0)
            {
                int index;
                if (dict.TryGetValue(n, out index))
                {
                    sb.Insert(index, '(');
                    sb.Append(')');
                    break;
                }
                else
                {
                    dict.Add(n, sb.Length);
                    n *= 10;
                    sb.Append(n / d);
                    n %= d;
                }
            }
        }
        return sb.ToString();
    }
}