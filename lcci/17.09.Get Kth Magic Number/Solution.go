func getKthMagicNumber(k int) int {
	dp := make([]int, k+1)
	dp[1] = 1
	p3, p5, p7 := 1, 1, 1
	for i := 2; i <= k; i++ {
		a, b, c := dp[p3]*3, dp[p5]*5, dp[p7]*7
		v := min(min(a, b), c)
		dp[i] = v
		if v == a {
			p3++
		}
		if v == b {
			p5++
		}
		if v == c {
			p7++
		}
	}
	return dp[k]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}