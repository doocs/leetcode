func countNumbersWithUniqueDigits(n int) int {
	dp := make([][]int, 10)
	for i := range dp {
		dp[i] = make([]int, 1<<11)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, mask int, lead bool) int {
		if pos <= 0 {
			return 1
		}
		if !lead && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		ans := 0
		for i := 0; i < 10; i++ {
			if ((mask >> i) & 1) == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead)
			} else {
				ans += dfs(pos-1, mask|1<<i, false)
			}
		}
		if !lead {
			dp[pos][mask] = ans
		}
		return ans
	}

	return dfs(n, 0, true)
}