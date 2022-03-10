func movingCount(m int, n int, k int) int {
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= n || vis[i][j] || (i%10+i/10+j%10+j/10) > k {
			return 0
		}
		vis[i][j] = true
		return 1 + dfs(i+1, j) + dfs(i, j+1)
	}
	return dfs(0, 0)
}