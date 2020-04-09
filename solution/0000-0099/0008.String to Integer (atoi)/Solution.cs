// https://leetcode.com/problems/string-to-integer-atoi/

public partial class Solution
{
    public int MyAtoi(string str)
    {
        int i = 0;
        long result = 0;
        bool minus = false;
        while (i < str.Length && char.IsWhiteSpace(str[i]))
        {
            ++i;
        }
        if (i < str.Length)
        {
            if (str[i] == '+')
            {
                ++i;
            }
            else if (str[i] == '-')
            {
                minus = true;
                ++i;
            }
        }
        while (i < str.Length && char.IsDigit(str[i]))
        {
            result = result * 10 + str[i] - '0';
            if (result > int.MaxValue)
            {
                break;
            }
            ++i;
        }
        if (minus) result = -result;
        if (result > int.MaxValue)
        {
            result = int.MaxValue;
        }
        if (result < int.MinValue)
        {
            result = int.MinValue;
        }
        return (int)result;
    }
}