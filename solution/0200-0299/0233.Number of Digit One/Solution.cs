public class Solution {
    public int CountDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        return CountDigitOne(n / 10 - 1) * 10 + n / 10 + CountDigitOneOfN(n / 10) * (n % 10 + 1) + (n % 10 >= 1 ? 1 : 0);
    }
    
    private int CountDigitOneOfN(int n) {
        var count = 0;
        while (n > 0)
        {
            if (n % 10 == 1) ++count;
            n /= 10;
        }
        return count;
    }
}