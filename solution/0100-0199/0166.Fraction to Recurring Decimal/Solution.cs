public class Solution {
    public string FractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        bool neg = (numerator > 0) ^ (denominator > 0);
        sb.Append(neg ? "-" : "");
        long a = Math.Abs((long)numerator), b = Math.Abs((long)denominator);
        sb.Append(a / b);
        a %= b;
        if (a == 0) {
            return sb.ToString();
        }
        sb.Append(".");
        Dictionary<long, int> d = new Dictionary<long, int>();
        while (a != 0) {
            d[a] = sb.Length;
            a *= 10;
            sb.Append(a / b);
            a %= b;
            if (d.ContainsKey(a)) {
                sb.Insert(d[a], "(");
                sb.Append(")");
                break;
            }
        }
        return sb.ToString();
    }
}