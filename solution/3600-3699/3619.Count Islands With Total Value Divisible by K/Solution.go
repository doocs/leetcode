func countIslands(grid [][]int, k int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		s := grid[i][j]
		grid[i][j] = 0
		for d := 0; d < 4; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				s += dfs(x, y)
			}
		}
		return s
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] > 0 && dfs(i, j)%k == 0 {
				ans++
			}
		}
	}
	return
}
