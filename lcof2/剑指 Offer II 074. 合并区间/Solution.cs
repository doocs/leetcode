public class Solution {
    public int[][] Merge(int[][] intervals) {
        intervals = intervals.OrderBy(a => a[0]).ToArray();
        int st = intervals[0][0], ed = intervals[0][1];
        var ans = new List<int[]>();
        for (int i = 1; i < intervals.Length; ++i)
        {
            int s = intervals[i][0], e = intervals[i][1];
            if (ed < s)
            {
                ans.Add(new int[]{st, ed});
                st = s;
                ed = e;
            }
            else
            {
                ed = Math.Max(ed, e);
            }
        }
        ans.Add(new int[]{st, ed});
        return ans.ToArray();
    }
}