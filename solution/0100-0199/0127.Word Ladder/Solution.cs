using System.Collections;
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public int LadderLength(string beginWord, string endWord, IList<string> wordList) {
        var words = Enumerable.Repeat(beginWord, 1).Concat(wordList).Select((word, i) => new { Word = word, Index = i }).ToList();
        var endWordIndex = words.Find(w => w.Word == endWord)?.Index;
        if (endWordIndex == null) {
            return 0;
        }

        var paths = new List<int>[words.Count];
        for (var i = 0; i < paths.Length; ++i)
        {
            paths[i] = new List<int>();
        }
        for (var i = 0; i < beginWord.Length; ++i)
        {
            var hashMap = new Hashtable();
            foreach (var item in words)
            {
                var newWord = string.Format("{0}_{1}", item.Word.Substring(0, i), item.Word.Substring(i + 1));
                List<int> similars;
                if (!hashMap.ContainsKey(newWord))
                {
                    similars = new List<int>();
                    hashMap.Add(newWord, similars);
                }
                else
                {
                    similars = (List<int>)hashMap[newWord];
                }
                foreach (var similar in similars)
                {
                    paths[similar].Add(item.Index);
                    paths[item.Index].Add(similar);
                }
                similars.Add(item.Index);
            }
        }

        var left = words.Count - 1;
        var lastRound = new List<int> { 0 };
        var visited = new bool[words.Count];
        visited[0] = true;
        for (var result = 2; left > 0; ++result)
        {
            var thisRound = new List<int>();
            foreach (var index in lastRound)
            {
                foreach (var next in paths[index])
                {
                    if (!visited[next])
                    {
                        visited[next] = true;
                        if (next == endWordIndex) return result;
                        thisRound.Add(next);
                    }
                }
            }
            if (thisRound.Count == 0) break;
            lastRound = thisRound;
        }

        return 0;
    }
}