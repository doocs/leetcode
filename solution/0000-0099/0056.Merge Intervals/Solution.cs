public class Solution {
    public int[][] Merge(int[][] intervals) {
        var res = new List<int[]>();
        int st = -1, ed = -1;
        foreach (var e in intervals.OrderBy(a => a[0]))
        {
            if (ed < e[0])
            {
                if (st != -1)
                {
                    res.Add(new int[] { st, ed });
                }
                st = e[0];
                ed = e[1];
            }
            else
            {
                ed = Math.Max(ed, e[1]);
            }
        }
        if (st != -1)
        {
            res.Add(new int[] { st, ed });
        }
        return res.ToArray();
    }
}