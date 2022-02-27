func numDistinctIslands(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	paths := make(map[string]bool)
	path := ""
	var dfs func(i, j, direction int)
	dfs = func(i, j, direction int) {
		grid[i][j] = 0
		path += strconv.Itoa(direction)
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 1; k < 5; k++ {
			x, y := i+dirs[k-1], j+dirs[k]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y, k)
			}
		}
		path += strconv.Itoa(direction)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				path = ""
				dfs(i, j, 0)
				paths[path] = true
			}
		}
	}
	return len(paths)
}