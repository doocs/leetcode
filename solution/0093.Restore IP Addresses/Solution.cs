using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<string> RestoreIpAddresses(string s) {
        if (s.Length > 12) return new List<string>();
        var results = new HashSet<string>();
        for (var i = 0; i < s.Length - 3; ++i)
        {
            for (var j = i + 1; j < s.Length - 2; ++j)
            {
                for (var k = j + 1; k < s.Length - 1; ++k)
                {
                    var part1 = Normalize(s.Substring(0, i + 1));
                    var part2 = Normalize(s.Substring(i + 1, j - i));
                    var part3 = Normalize(s.Substring(j + 1, k - j));
                    var part4 = Normalize(s.Substring(k + 1));
                    
                    if (part1 != null && part2 != null && part3 != null && part4 != null)
                    {
                        results.Add(string.Format("{0}.{1}.{2}.{3}", part1, part2, part3, part4));
                    }
                }
            }
        }
        
        return results.ToList();
    }
    
    private string Normalize(string part)
    {
        if (part == "0") return part;
        if (part[0] == '0') return null;
        byte temp = 0;
        if (byte.TryParse(part, out temp))
        {
            return part;
        }
        else
        {
            return null;
        }
    }
}