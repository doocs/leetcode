class Solution {
    public int countCornerRectangles(int[][] grid) {
        int n = grid[0].length;
        int ans = 0;
        Map<List<Integer>, Integer> cnt = new HashMap<>();
        for (var row : grid) {
            for (int i = 0; i < n; ++i) {
                if (row[i] == 1) {
                    for (int j = i + 1; j < n; ++j) {
                        if (row[j] == 1) {
                            List<Integer> t = List.of(i, j);
                            ans += cnt.getOrDefault(t, 0);
                            cnt.merge(t, 1, Integer::sum);
                        }
                    }
                }
            }
        }
        return ans;
    }
}