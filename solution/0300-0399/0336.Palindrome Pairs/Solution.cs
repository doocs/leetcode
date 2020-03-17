using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<int>> PalindromePairs(string[] words) {
        var results = new List<IList<int>>();
        var reverseDict = words.Select((w, i) => new {Word = w, Index = i}).ToDictionary(w => new string(w.Word.Reverse().ToArray()), w => w.Index);

        for (var i = 0; i < words.Length; ++i)
        {
            var word = words[i];
            for (var j = 0; j <= word.Length; ++j)
            {
                if (j > 0 && IsPalindrome(word, 0, j - 1))
                {
                    var suffix = word.Substring(j);
                    int pairIndex;
                    if (reverseDict.TryGetValue(suffix, out pairIndex) && i != pairIndex)
                    {
                        results.Add(new [] { pairIndex, i});
                    }
                }
                if (IsPalindrome(word, j, word.Length - 1))
                {
                    var prefix = word.Substring(0, j);
                    int pairIndex;
                    if (reverseDict.TryGetValue(prefix, out pairIndex) && i != pairIndex)
                    {
                        results.Add(new [] { i, pairIndex});
                    }
                }
            }
        }

        return results;
    }

    private bool IsPalindrome(string word, int startIndex, int endIndex)
    {
        var i = startIndex;
        var j = endIndex;
        while (i < j)
        {
            if (word[i] != word[j]) return false;
            ++i;
            --j;
        }
        return true;
    }
}