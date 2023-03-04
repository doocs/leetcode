func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 && (i == 0 || i == m-1 || j == 0 || j == n-1) {
				dfs(i, j)
			}
		}
	}
	for _, row := range grid {
		for _, v := range row {
			ans += v
		}
	}
	return
}