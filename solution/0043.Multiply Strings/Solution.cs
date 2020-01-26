using System.Text;

public class Solution {
    public string Multiply(string num1, string num2) {
        var digits = new int[num1.Length + num2.Length];
        for (var i = 0; i < num1.Length; ++i)
        {
            for (var j = 0; j < num2.Length; ++j)
            {
                var digit1 = num1[num1.Length - i - 1] - '0';
                var digit2 = num2[num2.Length - j - 1] - '0';
                var product = digit1 * digit2;
                digits[i + j] += product;
            }
        }
        
        var carry = 0;
        for (var i = 0; i < digits.Length; ++i)
        {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] %= 10;
        }
        
        var sb = new StringBuilder();
        for (var i = digits.Length - 1; i >= 0; --i)
        {
            if (digits[i] > 0 || sb.Length > 0)
            {
                sb.Append((char)(digits[i] + '0'));
            }
        }
        if (sb.Length == 0) sb.Append('0');
        return sb.ToString();
    }
}