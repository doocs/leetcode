func countDigitOne(n int) int {
	digit := make([]int, 0)
	for i := n; i > 0; i /= 10 {
		digit = append(digit, i%10)
	}

	dp := make([][]int, 10)
	for i := range dp {
		dp[i] = make([]int, 10)
		for j := 0; j < 10; j++ {
			dp[i][j] = -1
		}
	}

	var dfs func(pos, cnt int, limit bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos == -1 {
			return cnt
		}
		if !limit && dp[pos][cnt] != -1 {
			return dp[pos][cnt]
		}
		up := 9
		if limit {
			up = digit[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			next := cnt
			if i == 1 {
				next++
			}
			ans += dfs(pos-1, next, limit && i == digit[pos])
		}
		if !limit {
			dp[pos][cnt] = ans
		}
		return ans
	}

	return dfs(len(digit)-1, 0, true)
}