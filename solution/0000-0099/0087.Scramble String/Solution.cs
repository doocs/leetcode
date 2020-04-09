public class Solution {
    public bool IsScramble(string s1, string s2) {
        if (s1.Length != s2.Length) return false;
        var length = s1.Length;
        if (length == 0) return true;
        var f = new bool[length + 1, length, length];
        for (var i = 0; i < length; ++i)
        {
            for (var j = 0; j < length; ++j)
            {
                f[1, i, j] = s1[i] == s2[j];
            }
        }
        for (var i = 2; i <= length; ++i)
        {
            for (var j = 0; j <= length - i; ++j)
            {
                for (var k = 0; k <= length - i; ++k)
                {
                    for (var l = 1; l < i; ++l)
                    {
                        if (f[l, j, k] && f[i - l, j + l, k + l]
                            || f[l, j, k + i - l] && f[i - l, j + l, k])
                        {
                            f[i, j, k] = true;
                            break;
                        }
                    }
                }
            }
        }

        return f[length, 0, 0];
    }
}