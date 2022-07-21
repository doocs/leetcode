public class Solution {
    public int FindNthDigit(int n) {
        long length = 1;
        long count = 10;
        long tenBase = 9;
        long lastCount = 0;

        while (count < n)
        {
            length++;
            tenBase *= 10;
            var currentCount = tenBase * length;
            lastCount = count;
            count += currentCount;
        }

        var remainder = n - lastCount;
        var value = remainder / length;
        if (length > 1)
        {
            value += (int)Math.Pow(10, length - 1);
        }

        remainder %= length;
        return value.ToString()[(int)remainder] - '0';
    }
}
