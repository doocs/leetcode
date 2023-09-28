/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * type Robot struct {
 * }
 *
 * // Returns true if the cell in front is open and robot moves into the cell.
 * // Returns false if the cell in front is blocked and robot stays in the current cell.
 * func (robot *Robot) Move() bool {}
 *
 * // Robot will stay in the same cell after calling TurnLeft/TurnRight.
 * // Each turn will be 90 degrees.
 * func (robot *Robot) TurnLeft() {}
 * func (robot *Robot) TurnRight() {}
 *
 * // Clean the current cell.
 * func (robot *Robot) Clean() {}
 */

func cleanRoom(robot *Robot) {
	vis := map[[2]int]bool{}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(int, int, int)
	dfs = func(i, j, d int) {
		vis[[2]int{i, j}] = true
		robot.Clean()
		for k := 0; k < 4; k++ {
			nd := (d + k) % 4
			x, y := i+dirs[nd], j+dirs[nd+1]
			if !vis[[2]int{x, y}] && robot.Move() {
				dfs(x, y, nd)
				robot.TurnRight()
				robot.TurnRight()
				robot.Move()
				robot.TurnRight()
				robot.TurnRight()
			}
			robot.TurnRight()
		}
	}
	dfs(0, 0, 0)
}