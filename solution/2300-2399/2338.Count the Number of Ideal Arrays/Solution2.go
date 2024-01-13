func idealArrays(n int, maxValue int) int {
	mod := int(1e9) + 7
	c := make([][]int, n)
	for i := range c {
		c[i] = make([]int, 16)
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}
	dp := make([][]int, maxValue+1)
	for i := range dp {
		dp[i] = make([]int, 16)
		dp[i][1] = 1
	}
	for j := 1; j < 15; j++ {
		for i := 1; i <= maxValue; i++ {
			k := 2
			for ; k*i <= maxValue; k++ {
				dp[k*i][j+1] = (dp[k*i][j+1] + dp[i][j]) % mod
			}
		}
	}
	ans := 0
	for i := 1; i <= maxValue; i++ {
		for j := 1; j < 16; j++ {
			ans = (ans + dp[i][j]*c[n-1][j-1]) % mod
		}
	}
	return ans
}