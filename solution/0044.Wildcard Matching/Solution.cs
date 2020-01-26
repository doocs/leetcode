using System.Linq;

public class Solution {
    public bool IsMatch(string s, string p) {
        if (p.Count(ch => ch != '*') > s.Length)
        {
            return false;
        }
        
        bool[,] f = new bool[s.Length + 1, p.Length + 1];
        bool[] d = new bool[s.Length + 1]; // d[i] means f[0, j] || f[1, j] || ... || f[i, j]
        for (var j = 0; j <= p.Length; ++j)
        {
            d[0] = j == 0 ? true : d[0] && p[j - 1] == '*';
            for (var i = 0; i <= s.Length; ++i)
            {
                if (j == 0)
                {
                    f[i, j] = i == 0;
                    continue;
                }
                
                if (p[j - 1] == '*')
                {
                    if (i > 0)
                    {
                        d[i] = f[i, j - 1] || d[i - 1];
                    }
                    f[i, j] = d[i];
                }
                else if (p[j - 1] == '?')
                {
                    f[i, j] = i > 0 && f[i - 1, j - 1];
                }
                else
                {
                    f[i, j] = i > 0 && f[i - 1, j - 1] && s[i - 1] == p[j - 1];
                }
            }
        }
        return f[s.Length, p.Length];
    }
}