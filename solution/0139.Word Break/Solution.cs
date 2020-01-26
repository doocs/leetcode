using System.Collections.Generic;
using System.Linq;

public class Solution {
    public bool WordBreak(string s, ISet<string> wordDict) {
        var f = new bool[s.Length + 1];
        f[0] = true;
        var wordDictGroup = wordDict.GroupBy(word => word.Length);
        for (var i = 1; i <= s.Length; ++i)
        {
            foreach (var wordGroup in wordDictGroup)
            {
                var wordLength = wordGroup.Key;
                if (i >= wordLength && f[i - wordLength])
                {
                    foreach (var word in wordGroup)
                    {
                        if (s.Substring(i - wordLength, wordLength) == word)
                        {
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[s.Length];
    }
}