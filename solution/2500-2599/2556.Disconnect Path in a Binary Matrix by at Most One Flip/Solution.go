func isPossibleToCutPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i >= m || j >= n || grid[i][j] == 0 {
			return false
		}
		if i == m-1 && j == n-1 {
			return true
		}
		grid[i][j] = 0
		return dfs(i+1, j) || dfs(i, j+1)
	}
	a := dfs(0, 0)
	grid[0][0], grid[m-1][n-1] = 1, 1
	b := dfs(0, 0)
	return !(a && b)
}