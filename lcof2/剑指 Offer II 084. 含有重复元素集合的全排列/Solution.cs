using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<int>> PermuteUnique(int[] nums) {
        var results = new List<IList<int>>();
        var temp = new List<int>();
        var count = nums.GroupBy(n => n).ToDictionary(g => g.Key, g => g.Count());
        Search(count, temp, results);
        return results;
    }

    private void Search(Dictionary<int, int> count, IList<int> temp, IList<IList<int>> results)
    {
        if (!count.Any() && temp.Any())
        {
            results.Add(new List<int>(temp));
            return;
        }
        var keys = count.Keys.ToList();
        foreach (var key in keys)
        {
            temp.Add(key);
            --count[key];
            if (count[key] == 0) count.Remove(key);
            Search(count, temp, results);
            temp.RemoveAt(temp.Count - 1);
            if (count.ContainsKey(key))
            {
                ++count[key];
            }
            else
            {
                count[key] = 1;
            }
        }
    }
}