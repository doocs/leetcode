public class Solution {
    public IList<int> FindSubstring(string s, string[] words) {
        var wordsDict = new Dictionary<string, int>();
        foreach (var word in words)
        {
            if (!wordsDict.ContainsKey(word))
            {
                wordsDict.Add(word, 1);
            }
            else
            {
                ++wordsDict[word];
            }
        }
        
        var wordOfS = new string[s.Length];
        var wordLength = words[0].Length;
        var wordCount = words.Length;
        for (var i = 0; i <= s.Length - wordLength; ++i)
        {
            var substring = s.Substring(i, wordLength);
            if (wordsDict.ContainsKey(substring))
            {
                wordOfS[i] = substring;
            }
        }
        
        var result = new List<int>();
        for (var i = 0; i <= s.Length - wordLength * wordCount; ++i)
        {
            var tempDict = new Dictionary<string, int>(wordsDict);
            var tempCount = 0;
            for (var j = i; j <= i + wordLength * (wordCount - 1); j += wordLength)
            {
                if (wordOfS[j] != null && tempDict[wordOfS[j]] > 0)
                {
                    --tempDict[wordOfS[j]];
                    ++tempCount;
                }
                else
                {
                    break;
                }
            }
            if (tempCount == wordCount)
            {
                result.Add(i);
            }
        }
        
        return result;
    }
}