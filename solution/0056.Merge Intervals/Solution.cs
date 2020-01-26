using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<Interval> Merge(IList<Interval> intervals) {
        var result = new List<Interval>();
        foreach (var interval in intervals.OrderBy(i => i.start))
        {
            if (!result.Any())
            {
                result.Add(interval);
            }
            else
            {
                var last = result.Last();
                if (last.end < interval.start)
                {
                    result.Add(interval);
                }
                else if (last.end < interval.end)
                {
                    last.end = interval.end;
                }
            }
        }
        return result;
    }
}