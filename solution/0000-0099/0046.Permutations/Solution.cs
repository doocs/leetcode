using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<int>> Permute(int[] nums) {
        var results = new List<IList<int>>();
        var temp = new List<int>();
        var visited = new bool[nums.Length];
        Search(nums, visited, temp, results);
        return results;
    }

    private void Search(int[] nums, bool[] visited, IList<int> temp, IList<IList<int>> results)
    {
        int count = 0;
        for (var i = 0; i < nums.Length; ++i)
        {
            if (visited[i]) continue;
            ++count;
            temp.Add(nums[i]);
            visited[i] = true;
            Search(nums, visited, temp, results);
            temp.RemoveAt(temp.Count - 1);
            visited[i] = false;
        }
        if (count == 0 && temp.Any())
        {
            results.Add(new List<int>(temp));
        }
    }
}