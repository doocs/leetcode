/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * class Robot {
 *   public:
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     bool move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     void turnLeft();
 *     void turnRight();
 *
 *     // Clean the current cell.
 *     void clean();
 * };
 */

class Solution {
public:
    void cleanRoom(Robot& robot) {
        int dirs[5] = {-1, 0, 1, 0, -1};
        set<pair<int, int>> vis;
        function<void(int, int, int)> dfs = [&](int i, int j, int d) {
            robot.clean();
            vis.insert({i, j});
            for (int k = 0; k < 4; ++k) {
                int nd = (d + k) % 4;
                int x = i + dirs[nd], y = j + dirs[nd + 1];
                if (!vis.count({x, y}) && robot.move()) {
                    dfs(x, y, nd);
                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnRight();
                    robot.turnRight();
                }
                robot.turnRight();
            }
        };
        dfs(0, 0, 0);
    }
};