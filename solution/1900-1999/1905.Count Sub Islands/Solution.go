func countSubIslands(grid1 [][]int, grid2 [][]int) (ans int) {
	m, n := len(grid1), len(grid1[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		ok := grid1[i][j]
		grid2[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && dfs(x, y) == 0 {
				ok = 0
			}
		}
		return ok
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 {
				ans += dfs(i, j)
			}
		}
	}
	return
}