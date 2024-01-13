func countSpecialNumbers(n int) int {
	return f(n)
}

func f(n int) int {
	a := make([]int, 11)
	dp := make([][]int, 11)
	for i := range dp {
		dp[i] = make([]int, 1<<11)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(pos, mask int, lead, limit bool) int {
		if pos <= 0 {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		ans := 0
		up := 9
		if limit {
			up = a[pos]
		}
		for i := 0; i <= up; i++ {
			if ((mask >> i) & 1) == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead, limit && i == up)
			} else {
				ans += dfs(pos-1, mask|1<<i, false, limit && i == up)
			}
		}
		if !lead && !limit {
			dp[pos][mask] = ans
		}
		return ans
	}

	return dfs(l, 0, true, true)
}