class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int st = -1, ed = -1;
        List<int[]> res = new ArrayList<>();
        for (int[] e : intervals) {
            if (ed < e[0]) {
                if (st != -1) {
                    res.add(new int[]{st, ed});
                }
                st = e[0];
                ed = e[1];
            } else {
                ed = Math.max(ed, e[1]);
            }
        }
        if (st != -1) {
            res.add(new int[]{st, ed});
        }
        return res.toArray(new int[res.size()][]);
    }
}