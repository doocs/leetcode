public class Solution {
    public string MaxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9;
        int s = sum % 9;
        var ans = new StringBuilder(num);
        ans.Append('9', k);
        if (s > 0) {
            ans.Append(s);
        }
        ans.Append('0', num - ans.Length);
        return ans.ToString();
    }
}
