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
    vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    void cleanRoom(Robot& robot) {
        unordered_set<string> vis;
        dfs(0, 0, 0, vis, robot);
    }

    void dfs(int i, int j, int d, unordered_set<string>& vis, Robot& robot) {
        vis.insert(to_string(i) + "," + to_string(j));
        robot.clean();
        for (int k = 0; k < 4; ++k) {
            int nd = (d + k) % 4;
            int x = i + dirs[nd][0];
            int y = j + dirs[nd][1];
            if (!vis.count(to_string(x) + "," + to_string(y)) && robot.move()) {
                dfs(x, y, nd, vis, robot);
                back(robot);
            }
            robot.turnRight();
        }
    }

    void back(Robot& robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
};