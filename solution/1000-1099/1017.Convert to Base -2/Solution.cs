public class Solution {
    public string BaseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        StringBuilder ans = new StringBuilder();
        int num = n;
        while (num != 0) {
            if (num % 2 != 0) {
                ans.Append('1');
                num -= k;
            } else {
                ans.Append('0');
            }
            k *= -1;
            num /= 2;
        }
        char[] cs = ans.ToString().ToCharArray();
        Array.Reverse(cs);
        return new string(cs);
    }
}