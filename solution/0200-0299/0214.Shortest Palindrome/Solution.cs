// https://leetcode.com/problems/shortest-palindrome/

using System.Text;

public partial class Solution
{
    public string ShortestPalindrome(string s)
    {
        for (var i = s.Length - 1; i >= 0; --i)
        {
            var k = i;
            var j = 0;
            while (j < k)
            {
                if (s[j] == s[k])
                {
                    ++j;
                    --k;
                }
                else
                {
                    break;
                }
            }
            if (j >= k)
            {
                var sb = new StringBuilder(s.Length * 2 - i - 1);
                for (var l = s.Length - 1; l >= i + 1; --l)
                {
                    sb.Append(s[l]);
                }
                sb.Append(s);
                return sb.ToString();
            }
        }

        return string.Empty;
    }
}