func maximumProfit(prices []int, k int) int64 {
	n := len(prices)
	f := make([][][3]int, n)
	for i := range f {
		f[i] = make([][3]int, k+1)
	}

	for j := 1; j <= k; j++ {
		f[0][j][1] = -prices[0]
		f[0][j][2] = prices[0]
	}

	for i := 1; i < n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j][0] = max(f[i-1][j][0], f[i-1][j][1]+prices[i], f[i-1][j][2]-prices[i])
			f[i][j][1] = max(f[i-1][j][1], f[i-1][j-1][0]-prices[i])
			f[i][j][2] = max(f[i-1][j][2], f[i-1][j-1][0]+prices[i])
		}
	}

	return int64(f[n-1][k][0])
}