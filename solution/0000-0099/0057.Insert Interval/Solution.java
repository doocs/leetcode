class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int st = newInterval[0], ed = newInterval[1];
        boolean insert = false;
        for (int[] interval : intervals) {
            int s = interval[0], e = interval[1];
            if (ed < s) {
                if (!insert) {
                    ans.add(new int[]{st, ed});
                    insert = true;
                }
                ans.add(interval);
            } else if (e < st) {
                ans.add(interval);
            } else {
                st = Math.min(st, s);
                ed = Math.max(ed, e);
            }
        }
        if (!insert) {
            ans.add(new int[]{st, ed});
        }
        return ans.toArray(new int[ans.size()][]);
    }
}