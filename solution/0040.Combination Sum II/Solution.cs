using System;
using System.Collections.Generic;
using System.Linq;

public class Solution
{
    public IList<IList<int>> CombinationSum2(int[] candidates, int target)
    {
        var dict = new SortedDictionary<int, int>(candidates.GroupBy(c => c).ToDictionary(g => g.Key, g => g.Count()));
        var paths = new List<Tuple<int, int>>[target + 1];
        paths[0] = new List<Tuple<int, int>>();
        foreach (var pair in dict)
        {
            for (var j = target; j >= 0; --j)
            {
                for (var k = 1; k <= pair.Value && j - pair.Key * k >= 0; ++k)
                {
                    if (paths[j - pair.Key * k] != null)
                    {
                        if (paths[j] == null)
                        {
                            paths[j] = new List<Tuple<int, int>>();
                        }
                        paths[j].Add(Tuple.Create(pair.Key, k));
                    }
                }
            }
        }

        var results = new List<IList<int>>();
        if (paths[target] != null) GenerateResults(results, new Stack<int>(), paths, target, paths[target].Count - 1);
        return results;
    }

    private void GenerateResults(IList<IList<int>> results, Stack<int> result, List<Tuple<int, int>>[] paths, int remaining,
        int maxIndex)
    {
        if (remaining == 0)
        {
            results.Add(new List<int>(result));
            return;
        }
        for (var i = maxIndex; i >= 0; --i)
        {
            var path = paths[remaining][i];
            for (var j = 0; j < path.Item2; ++j)
            {
                result.Push(path.Item1);
            }
            var nextMaxIndex = paths[remaining - path.Item1 * path.Item2].BinarySearch(Tuple.Create(path.Item1, int.MinValue), Comparer.Instance);
            nextMaxIndex = ~nextMaxIndex - 1;
            GenerateResults(results, result, paths, remaining - path.Item1 * path.Item2, nextMaxIndex);
            for (var j = 0; j < path.Item2; ++j)
            {
                result.Pop();
            }
        }
    }
}

class Comparer : IComparer<Tuple<int, int>>
{
    public int Compare(Tuple<int, int> x, Tuple<int, int> y)
    {
        if (x.Item1 < y.Item1) return -1;
        if (x.Item1 > y.Item1) return 1;
        return x.Item2.CompareTo(y.Item2);
    }

    public static Comparer Instance = new Comparer();
}