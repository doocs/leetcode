func rotatedDigits(n int) int {
	a := make([]int, 6)
	dp := make([][]int, 6)
	for i := range a {
		dp[i] = make([]int, 2)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	l := 1
	for n > 0 {
		a[l] = n % 10
		n /= 10
		l++
	}

	var dfs func(int, int, bool) int
	dfs = func(pos, ok int, limit bool) int {
		if pos <= 0 {
			return ok
		}
		if !limit && dp[pos][ok] != -1 {
			return dp[pos][ok]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 || i == 1 || i == 8 {
				ans += dfs(pos-1, ok, limit && i == up)
			}
			if i == 2 || i == 5 || i == 6 || i == 9 {
				ans += dfs(pos-1, 1, limit && i == up)
			}
		}
		if !limit {
			dp[pos][ok] = ans
		}
		return ans
	}

	return dfs(l, 0, true)
}