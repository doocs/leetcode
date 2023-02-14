func closedIsland(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		res := 1
		if i == 0 || i == m-1 || j == 0 || j == n-1 {
			res = 0
		}
		grid[i][j] = 1
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 {
				res &= dfs(x, y)
			}
		}
		return res
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				ans += dfs(i, j)
			}
		}
	}
	return
}