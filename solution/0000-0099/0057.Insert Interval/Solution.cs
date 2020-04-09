using System;
using System.Collections.Generic;

public class Solution {
    public int[][] Insert(int[][] intervals, int[] newInterval) {
        var result = new List<int[]>();
        var i = 0;

        while (i < intervals.Length && intervals[i][1] < newInterval[0])
        {
            result.Add(intervals[i++]);
        }

        while (i < intervals.Length && intervals[i][0] <= newInterval[1] && intervals[i][1] >= newInterval[0])
        {
            newInterval[0] = Math.Min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.Max(intervals[i][1], newInterval[1]);
            ++i;
        }
        result.Add(newInterval);

        while (i < intervals.Length)
        {
            result.Add(intervals[i++]);
        }

        return result.ToArray();
    }
}