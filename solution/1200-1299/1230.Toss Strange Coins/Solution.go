func probabilityOfHeads(prob []float64, target int) float64 {
	dp := make([]float64, target+1)
	dp[0] = 1
	for _, v := range prob {
		for j := target; j >= 0; j-- {
			dp[j] *= (1 - v)
			if j >= 1 {
				dp[j] += dp[j-1] * v
			}
		}
	}
	return dp[target]
}