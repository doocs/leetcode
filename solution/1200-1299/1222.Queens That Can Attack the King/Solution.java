class Solution {
    private static final int N = 8;
    private int[][] dirs
        = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> s = get(queens);
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] dir : dirs) {
            int x = king[0], y = king[1];
            int a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < N && y + b >= 0 && y + b < N) {
                x += a;
                y += b;
                if (s.contains(x * N + y)) {
                    ans.add(Arrays.asList(x, y));
                    break;
                }
            }
        }
        return ans;
    }

    private Set<Integer> get(int[][] queens) {
        Set<Integer> ans = new HashSet<>();
        for (int[] queen : queens) {
            ans.add(queen[0] * N + queen[1]);
        }
        return ans;
    }
}