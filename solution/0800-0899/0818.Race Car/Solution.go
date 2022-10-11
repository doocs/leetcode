func racecar(target int) int {
	dp := make([]int, target+1)
	for i := 1; i <= target; i++ {
		k := bits.Len(uint(i))
		if i == (1<<k)-1 {
			dp[i] = k
			continue
		}
		dp[i] = dp[(1<<k)-1-i] + k + 1
		for j := 0; j < k; j++ {
			dp[i] = min(dp[i], dp[i-(1<<(k-1))+(1<<j)]+k-1+j+2)
		}
	}
	return dp[target]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}