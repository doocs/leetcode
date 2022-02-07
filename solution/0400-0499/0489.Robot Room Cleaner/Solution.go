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
	vis := make(map[string]bool)
	dirs := [][]int{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
	back := func() {
		robot.TurnRight()
		robot.TurnRight()
		robot.Move()
		robot.TurnRight()
		robot.TurnRight()
	}
	var dfs func(i, j, d int)
	dfs = func(i, j, d int) {
		vis[strconv.Itoa(i)+","+strconv.Itoa(j)] = true
		robot.Clean()
		for k := 0; k < 4; k++ {
			nd := (d + k) % 4
			x, y := i+dirs[nd][0], j+dirs[nd][1]
			if !vis[strconv.Itoa(x)+","+strconv.Itoa(y)] && robot.Move() {
				dfs(x, y, nd)
				back()
			}
			robot.TurnRight()
		}
	}
	dfs(0, 0, 0)
}