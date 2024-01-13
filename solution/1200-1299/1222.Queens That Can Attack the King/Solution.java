class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int n = 8;
        var s = new boolean[n][n];
        for (var q : queens) {
            s[q[0]][q[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a != 0 || b != 0) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.add(List.of(x, y));
                            break;
                        }
                        x += a;
                        y += b;
                    }
                }
            }
        }
        return ans;
    }
}