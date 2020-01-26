using System.Collections.Generic;
using System.Linq;

public class Solution {
    public int[][] Merge(int[][] intervals) {
        var result = new List<int[]>();
        foreach (var interval in intervals.OrderBy(i => i[0]))
        {
            if (!result.Any())
            {
                result.Add(interval);
            }
            else
            {
                var last = result.Last();
                if (last[1] < interval[0])
                {
                    result.Add(interval);
                }
                else if (last[1] < interval[1])
                {
                    last[1] = interval[1];
                }
            }
        }
        return result.ToArray();
    }
}