using System.Collections.Generic;

public class Solution
{
    public IList<IList<int>> CombinationSum3(int k, int n)
    {
        if (n == 0 || k == 0) return new IList<int>[0];

        var paths = new List<int>[n + 1, k + 1];
        paths[0, 0] = new List<int>();
        for (var c = 1; c <= 9; ++c)
        {
            for (var j = n; j >= c; --j)
            {
                for (var kk = 1; kk <= k; ++kk)
                {
                    if (paths[j - c, kk - 1] != null)
                    {
                        if (paths[j, kk] == null)
                        {
                            paths[j, kk] = new List<int>();
                        }
                        paths[j, kk].Add(c);
                    }
                }
            }
        }

        var results = new List<IList<int>>();
        if (paths[n, k] != null && paths[n, k].Count > 0) GenerateResults(results, new Stack<int>(), paths, k, n, paths[n, k].Count - 1);
        return results;
    }

    private void GenerateResults(IList<IList<int>> results, Stack<int> result, List<int>[,] paths, int k, int n,
        int maxIndex)
    {
        if (n == 0)
        {
            results.Add(new List<int>(result));
            return;
        }
        for (var i = maxIndex; i >= 0; --i)
        {
            var value = paths[n, k][i];
            result.Push(value);
            var nextMaxIndex = paths[n - value, k - 1].BinarySearch(value);
            if (nextMaxIndex >= 0)
            {
                --nextMaxIndex;
            }
            else if (nextMaxIndex < 0)
            {
                nextMaxIndex = ~nextMaxIndex - 1;
            }
            GenerateResults(results, result, paths, k - 1, n - value, nextMaxIndex);
            result.Pop();
        }
    }
}