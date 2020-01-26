public class Solution {
    public bool IsMatch(string s, string p) {
        var f = new bool[s.Length + 1, p.Length + 1];
        f[0, 0] = true;
        for (var i = 0; i <= s.Length; ++i)
        {
            for (var j = 0; j <= p.Length; ++j)
            {
                if (i != 0 || j != 0)
                {
                    if (j == 0)
                    {
                        f[i, j] = false;
                    }
                    else if (i == 0)
                    {
                        if (p[j - 1] == '*')
                        {
                            f[i, j] = f[i, j - 2];
                        }
                        else
                        {
                            f[i, j] = false;
                        }
                    }
                    else
                    {
                        if (p[j - 1] == '.')
                        {
                            f[i, j] = f[i - 1, j - 1];
                        }
                        else if (p[j - 1] == '*')
                        {
                            f[i, j] = f[i - 1, j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.') || f[i, j - 2];
                        }
                        else
                        {
                            f[i, j] = f[i - 1, j - 1] && s[i - 1] == p[j - 1];
                        }
                    }
                }
            }
        }
        
        return f[s.Length, p.Length];
    }
}