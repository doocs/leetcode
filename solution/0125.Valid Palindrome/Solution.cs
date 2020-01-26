using System.Linq;

public class Solution {
    public bool IsPalindrome(string s) {
        var chars = s.Where(ch => char.IsLetterOrDigit(ch)).Select(char.ToLower).ToList();
        var i = 0;
        var j = chars.Count - 1;
        for (; i < j; ++i, --j)
        {
            if (chars[i] != chars[j]) return false;
        }
        return true;
    }
}