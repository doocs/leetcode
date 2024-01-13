func sellingWood(m int, n int, prices [][]int) int64 {
	d := make([][]int, m+1)
	dp := make([][]int, m+1)
	for i := range d {
		d[i] = make([]int, n+1)
		dp[i] = make([]int, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] = d[i][j]
			for k := 1; k < i; k++ {
				dp[i][j] = max(dp[i][j], dp[k][j]+dp[i-k][j])
			}
			for k := 1; k < j; k++ {
				dp[i][j] = max(dp[i][j], dp[i][k]+dp[i][j-k])
			}
		}
	}
	return int64(dp[m][n])
}