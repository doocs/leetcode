class Solution {
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int N = (int) 1e6;
    private Set<Integer> blocked;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        this.blocked = new HashSet<>();
        for (int[] b : blocked) {
            this.blocked.add(b[0] * N + b[1]);
        }
        return dfs(source, target, new HashSet<>()) && dfs(target, source, new HashSet<>());
    }

    private boolean dfs(int[] source, int[] target, Set<Integer> seen) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || blocked.contains(sx * N + sy) || seen.contains(sx * N + sy)) {
            return false;
        }
        seen.add(sx * N + sy);
        if (seen.size() > 20000 || (sx == target[0] && sy == target[1])) {
            return true;
        }
        for (int[] dir : dirs) {
            if (dfs(new int[]{sx + dir[0], sy + dir[1]}, target, seen)) {
                return true;
            }
        }
        return false;
    }
}