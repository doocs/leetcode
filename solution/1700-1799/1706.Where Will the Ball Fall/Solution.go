func findBall(grid [][]int) []int {
	m, n := len(grid), len(grid[0])

	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == m {
			return j
		}
		if j == 0 && grid[i][j] == -1 {
			return -1
		}
		if j == n-1 && grid[i][j] == 1 {
			return -1
		}
		if grid[i][j] == 1 && grid[i][j+1] == -1 {
			return -1
		}
		if grid[i][j] == -1 && grid[i][j-1] == 1 {
			return -1
		}
		if grid[i][j] == 1 {
			return dfs(i+1, j+1)
		}
		return dfs(i+1, j-1)
	}

	var ans []int
	for j := 0; j < n; j++ {
		ans = append(ans, dfs(0, j))
	}
	return ans
}