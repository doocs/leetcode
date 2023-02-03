func nthUglyNumber(n int) int {
	dp := make([]int, n)
	dp[0] = 1
	p2, p3, p5 := 0, 0, 0
	for i := 1; i < n; i++ {
		next2, next3, next5 := dp[p2]*2, dp[p3]*3, dp[p5]*5
		dp[i] = min(next2, min(next3, next5))
		if dp[i] == next2 {
			p2++
		}
		if dp[i] == next3 {
			p3++
		}
		if dp[i] == next5 {
			p5++
		}
	}
	return dp[n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}