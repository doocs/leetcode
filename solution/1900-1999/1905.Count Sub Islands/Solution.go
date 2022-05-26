func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	ans := 0
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		res := grid1[i][j] == 1
		grid2[i][j] = 0
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !dfs(x, y) {
				res = false
			}
		}
		return res
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && dfs(i, j) {
				ans++
			}
		}
	}
	return ans
}