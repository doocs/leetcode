using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<string>> Partition(string s) {
        if (s.Length == 0) return new List<IList<string>>();
            
        var paths = new List<int>[s.Length];
        for (var i = 0; i < s.Length; ++i)
        {
            int j, k;
            for (j = i, k = i + 1; j >= 0 && k < s.Length; --j, ++k)
            {
                if (s[j] == s[k])
                {
                    if (paths[k] == null)
                    {
                        paths[k] = new List<int>();
                    }
                    paths[k].Add(j - 1);
                }
                else
                {
                    break;
                }
            }
            for (j = i, k = i; j >= 0 && k < s.Length; --j, ++k)
            {
                if (s[j] == s[k])
                {
                    if (paths[k] == null)
                    {
                        paths[k] = new List<int>();
                    }
                    paths[k].Add(j - 1);
                }
                else
                {
                    break;
                }
            }
        }
        
        var prevs = new List<int>[s.Length];
        for (var i = 0; i < s.Length; ++i)
        {
            if (paths[i] != null)
            {
                foreach (var path in paths[i])
                {
                    if (path < 0 || prevs[path] != null)
                    {
                        if (prevs[i] == null)
                        {
                            prevs[i] = new List<int>();
                        }
                        prevs[i].Add(path);
                    }
                }
            }
        }
        
        var results = new List<IList<string>>();
        var temp = new List<string>();
        GenerateResults(prevs, s, s.Length - 1, temp, results);
        return results;
    }
    
    private void GenerateResults(List<int>[] prevs, string s, int i, IList<string> temp, IList<IList<string>> results)
    {
        if (i < 0)
        {
            results.Add(temp.Reverse().ToList());
        }
        else
        {
            foreach (var prev in prevs[i])
            {
                temp.Add(s.Substring(prev + 1, i - prev));
                GenerateResults(prevs, s, prev, temp, results);
                temp.RemoveAt(temp.Count - 1);
            }
        }
    }
}