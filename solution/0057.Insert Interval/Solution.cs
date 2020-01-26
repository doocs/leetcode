using System;
using System.Collections.Generic;

public class Solution {
    public IList<Interval> Insert(IList<Interval> intervals, Interval newInterval) {
        var result = new List<Interval>();
        var i = 0;

        while (i < intervals.Count && intervals[i].end < newInterval.start)
        {
            result.Add(intervals[i++]);
        }

        while (i < intervals.Count && intervals[i].start <= newInterval.end && intervals[i].end >= newInterval.start)
        {
            newInterval.start = Math.Min(intervals[i].start, newInterval.start);
            newInterval.end = Math.Max(intervals[i].end, newInterval.end);
            ++i;
        }
        result.Add(newInterval);

        while (i < intervals.Count)
        {
            result.Add(intervals[i++]);
        }

        return result;
    }
}