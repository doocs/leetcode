using System;
using System.Collections.Generic;

public class Solution {
    public int MinCut(string s) {
        if (s.Length == 0) return 0;
            
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
        
        var partCount = new int[s.Length];
        for (var i = 0; i < s.Length; ++i)
        {
            partCount[i] = int.MaxValue;
            if (paths[i] != null)
            {
                foreach (var path in paths[i])
                {
                    if (path < 0)
                    {
                        partCount[i] = 0;
                        break;
                    }
                    else
                    {
                        partCount[i] = Math.Min(partCount[i], partCount[path]);
                    }
                }
            }
            ++partCount[i];
        }
        return partCount[s.Length - 1] - 1;        
    }
}