using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Node
{
    public int Index1 { get; set; }
    public int Index2 { get; set; }
}

public class Solution {
    public IList<string> WordBreak(string s, IList<string> wordDict) {
        var paths = new List<Tuple<int, string>>[s.Length + 1];
        paths[s.Length] = new List<Tuple<int, string>> { Tuple.Create(-1, (string)null) };
        var wordDictGroup = wordDict.GroupBy(word => word.Length);
        for (var i = s.Length - 1; i >= 0; --i)
        {
            paths[i] = new List<Tuple<int, string>>();
            foreach (var wordGroup in wordDictGroup)
            {
                var wordLength = wordGroup.Key;
                if (i + wordLength <= s.Length && paths[i + wordLength].Count > 0)
                {
                    foreach (var word in wordGroup)
                    {
                        if (s.Substring(i, wordLength) == word)
                        {
                            paths[i].Add(Tuple.Create(i + wordLength, word));
                        }
                    }
                }
            }
        }

        return GenerateResults(paths);
    }

    private IList<string> GenerateResults(List<Tuple<int, string>>[] paths)
    {
        var results = new List<string>();
        var sb = new StringBuilder();
        var stack = new Stack<Node>();
        stack.Push(new Node());
        while (stack.Count > 0)
        {
            var node = stack.Peek();
            if (node.Index1 == paths.Length - 1 || node.Index2 == paths[node.Index1].Count)
            {
                if (node.Index1 == paths.Length - 1)
                {
                    results.Add(sb.ToString());
                }
                stack.Pop();
                if (stack.Count > 0)
                {
                    var parent = stack.Peek();
                    var length = paths[parent.Index1][parent.Index2 - 1].Item2.Length;
                    if (length < sb.Length) ++length;
                    sb.Remove(sb.Length - length, length);
                }
            }
            else
            {
                var newNode = new Node { Index1 = paths[node.Index1][node.Index2].Item1, Index2 = 0 };
                if (sb.Length != 0)
                {
                    sb.Append(' ');
                }
                sb.Append(paths[node.Index1][node.Index2].Item2);
                stack.Push(newNode);
                ++node.Index2;
            }
        }
        return results;
    }
}