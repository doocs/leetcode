public class Solution {
    public int StrToInt(string str) {
        if (string.IsNullOrWhiteSpace(str))
        {
            return 0;
        }

        str = str.Trim();
        int currentIndex = 0;
        bool minus = false;
        if (str[currentIndex] == '+')
        {
            minus = false;
            currentIndex++;
        }
        else if (str[currentIndex] == '-')
        {
            minus = true;
            currentIndex++;
        }

        long result = 0;
        for (; currentIndex < str.Length; currentIndex++)
        {
            if (!char.IsDigit(str[currentIndex]))
            {
                break;
            }

            if (minus && result - 1 > int.MaxValue)
            {
                return int.MinValue;
            }
            else if (!minus && result > int.MaxValue)
            {
                return int.MaxValue;
            }

            result = result * 10 + (str[currentIndex] - '0');
        }

        if (minus && result - 1 > int.MaxValue)
        {
            return int.MinValue;
        }
        else if (!minus && result > int.MaxValue)
        {
            return int.MaxValue;
        }
        else
        {
            return (minus ? -1 : 1) * (int)result;
        }

    }
}