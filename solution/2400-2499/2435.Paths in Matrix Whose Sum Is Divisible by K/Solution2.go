func numberOfPaths(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	var mod int = 1e9 + 7
	dp := make([][][]int, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, k)
		}
	}
	dp[0][0][grid[0][0]%k] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for s := 0; s < k; s++ {
				t := ((s - grid[i][j]%k) + k) % k
				if i > 0 {
					dp[i][j][s] += dp[i-1][j][t]
				}
				if j > 0 {
					dp[i][j][s] += dp[i][j-1][t]
				}
				dp[i][j][s] %= mod
			}
		}
	}
	return dp[m-1][n-1][0]
}