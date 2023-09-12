func numDistinctIslands(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	paths := map[string]bool{}
	path := []byte{}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j, k int)
	dfs = func(i, j, k int) {
		grid[i][j] = 0
		path = append(path, byte(k))
		for h := 1; h < 5; h++ {
			x, y := i+dirs[h-1], j+dirs[h]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y, h)
			}
		}
		path = append(path, byte(k))
	}
	for i, row := range grid {
		for j, x := range row {
			if x == 1 {
				dfs(i, j, 0)
				paths[string(path)] = true
				path = path[:0]
			}
		}
	}
	return len(paths)
}