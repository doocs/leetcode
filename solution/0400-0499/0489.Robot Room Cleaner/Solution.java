/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};
    private Set<List<Integer>> vis = new HashSet<>();
    private Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(0, 0, 0);
    }

    private void dfs(int i, int j, int d) {
        robot.clean();
        vis.add(List.of(i, j));
        for (int k = 0; k < 4; ++k) {
            int nd = (d + k) % 4;
            int x = i + dirs[nd], y = j + dirs[nd + 1];
            if (!vis.contains(List.of(x, y)) && robot.move()) {
                dfs(x, y, nd);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }
}