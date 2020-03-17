using System;
using System.Collections.Generic;
using System.Linq;

public class Solution
{
    public IList<IList<int>> CombinationSum(int[] candidates, int target)
    {
        Array.Sort(candidates);
        candidates = candidates.Distinct().ToArray();

        var paths = new List<int>[target + 1];
        paths[0] = new List<int>();
        foreach (var c in candidates)
        {
            for (var j = c; j <= target; ++j)
            {
                if (paths[j - c] != null)
                {
                    if (paths[j] == null)
                    {
                        paths[j] = new List<int>();
                    }
                    paths[j].Add(c);
                }
            }
        }

        var results = new List<IList<int>>();
        if (paths[target] != null) GenerateResults(results, new Stack<int>(), paths, target, paths[target].Count - 1);
        return results;
    }

    private void GenerateResults(IList<IList<int>> results, Stack<int> result, List<int>[] paths, int remaining,
        int maxIndex)
    {
        if (remaining == 0)
        {
            results.Add(new List<int>(result));
            return;
        }
        for (var i = maxIndex; i >= 0; --i)
        {
            var value = paths[remaining][i];
            result.Push(value);
            var nextMaxIndex = paths[remaining - value].BinarySearch(value);
            if (nextMaxIndex < 0)
            {
                nextMaxIndex = ~nextMaxIndex - 1;
            }
            GenerateResults(results, result, paths, remaining - value, nextMaxIndex);
            result.Pop();
        }
    }
}