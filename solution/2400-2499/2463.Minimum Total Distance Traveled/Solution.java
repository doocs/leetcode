class Solution {
    private long[][] f;
    private List<Integer> robot;
    private int[][] factory;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        this.robot = robot;
        this.factory = factory;
        f = new long[robot.size()][factory.length];
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i == robot.size()) {
            return 0;
        }
        if (j == factory.length) {
            return Long.MAX_VALUE / 1000;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        long ans = dfs(i, j + 1);
        long t = 0;
        for (int k = 0; k < factory[j][1]; ++k) {
            if (i + k == robot.size()) {
                break;
            }
            t += Math.abs(robot.get(i + k) - factory[j][0]);
            ans = Math.min(ans, t + dfs(i + k + 1, j + 1));
        }
        f[i][j] = ans;
        return ans;
    }
}