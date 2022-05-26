func dicesProbability(n int) []float64 {
	dp := make([]float64, 7)
	for i := 1; i <= 6; i++ {
		dp[i] = 1.0 / 6.0
	}
	for i := 2; i <= n; i++ {
		n := len(dp)
		tmp := make([]float64, 6*i+1)
		for j := 0; j < n; j++ {
			for k := 1; k <= 6; k++ {
				tmp[j+k] += dp[j] / 6.0
			}
		}
		dp = tmp
	}
	return dp[n:]
}
