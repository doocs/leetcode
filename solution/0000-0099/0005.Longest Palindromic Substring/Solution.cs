public class Solution {
    public string LongestPalindrome(string s) {
        var f = new bool[s.Length];
        var maxLen = 0;
        var index = 0;
        for (var p = 0; p <= 1; ++p)
        {
            for (var l = 1 + p; l <= s.Length; l += 2)
            {
                for (var i = 0; i <= s.Length - l; ++i)
                {
                    if (l <= 2)
                    {
                        f[i] = s[i] == s[i + l - 1]; 
                    }
                    else
                    {
                        f[i] = f[i + 1] && s[i] == s[i + l - 1]; 
                    }
                    if (f[i] && l > maxLen)
                    {
                        maxLen = l;
                        index = i;
                    }
                }
            }
        }
        
        return s.Substring(index, maxLen);
    }
}