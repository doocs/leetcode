using System.Linq;

public class Solution {
    public string MinWindow(string s, string t) {
        var dict = t.Distinct().ToDictionary(ch => ch, ch => 0);
        var goalDict = t.GroupBy(ch => ch).ToDictionary(g => g.Key, g => g.Count());
        var goal = goalDict.Count;
        
        var minI = int.MaxValue;
        var minJ = 0;
        var i = 0;
        var j = 0;
        var current = 0;
        
        while (true)
        {
            while (current < goal && i < s.Length)
            {
                if (dict.ContainsKey(s[i]))
                {
                    if (++dict[s[i]] == goalDict[s[i]])
                    {
                        ++current;
                    }
                }
                ++i;
            }
            
            while (current == goal && j < s.Length)
            {
                if (dict.ContainsKey(s[j]))
                {
                    if (dict[s[j]] == goalDict[s[j]])
                    {
                        break;
                    }
                    else
                    {
                        --dict[s[j]];
                    }
                }
                ++j;
            }
            
            if (current == goal)
            {
                if (i - j < minI - minJ)
                {
                    minI = i;
                    minJ = j;
                }
                --dict[s[j]];
                --current;
                ++j;
            }
            else
            {
                break;
            }
        }
        
        if (minI == int.MaxValue)
        {
            return string.Empty;
        }
        else
        {
            return s.Substring(minJ, minI - minJ);
        }
    }
}