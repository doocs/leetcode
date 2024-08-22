class Solution {
    public int winningPlayerCount(int n, int[][] pick) {
        int[][] cnt = new int[n][11];
        Set<Integer> s = new HashSet<>();
        for (var p : pick) {
            int x = p[0], y = p[1];
            if (++cnt[x][y] > x) {
                s.add(x);
            }
        }
        return s.size();
    }
}
