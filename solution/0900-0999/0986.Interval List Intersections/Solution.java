class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int m = firstList.length, n = secondList.length;
        for (int i = 0, j = 0; i < m && j < n;) {
            int l = Math.max(firstList[i][0], secondList[j][0]);
            int r = Math.min(firstList[i][1], secondList[j][1]);
            if (l <= r) {
                ans.add(new int[] {l, r});
            }
            if (firstList[i][1] < secondList[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}