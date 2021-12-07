func colorBorder(grid [][]int, row int, col int, color int) [][]int {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := 0; i < m; i++ {
		vis[i] = make([]bool, n)
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}

	var dfs func(i, j, color int)
	dfs = func(i, j, color int) {
		vis[i][j] = true
		oldColor := grid[i][j]
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if !vis[x][y] {
					if grid[x][y] == oldColor {
						dfs(x, y, color)
					} else {
						grid[i][j] = color
					}
				}
			} else {
				grid[i][j] = color
			}
		}
	}
	dfs(row, col, color)
	return grid
}