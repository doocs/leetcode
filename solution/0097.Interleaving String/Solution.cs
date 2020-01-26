using System;

public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        if (s1.Length + s2.Length != s3.Length) return false;
        var f = new bool[s3.Length + 1, s1.Length + 1];
        f[0, 0] = true;
        for (var i = 1; i <= s3.Length; ++i)
        {
            var j = Math.Max(0, i - s2.Length);
            var k = i - j;
            while (j <= s1.Length)
            {
                if (j > 0 && s3[i - 1] == s1[j - 1] && f[i - 1, j - 1])
                {
                    f[i, j] = true;
                }
                else if (k > 0 && s3[i - 1] == s2[k - 1] && f[i - 1, j])
                {
                    f[i, j] = true;
                }
                ++j;
                --k;
            }
        }
        
        for (var i = 0; i <= s1.Length; ++i)
        {
            if (f[s3.Length, i]) return true;
        }
        return false;
    }
}