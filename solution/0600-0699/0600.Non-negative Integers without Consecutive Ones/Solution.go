func findIntegers(n int) int {
	a := make([]int, 33)
	dp := make([][2]int, 33)
	for i := range dp {
		dp[i] = [2]int{-1, -1}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n & 1
		n >>= 1
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, pre int, limit bool) int {
		if pos <= 0 {
			return 1
		}
		if !limit && dp[pos][pre] != -1 {
			return dp[pos][pre]
		}
		up := 1
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if !(pre == 1 && i == 1) {
				ans += dfs(pos-1, i, limit && i == up)
			}
		}
		if !limit {
			dp[pos][pre] = ans
		}
		return ans
	}
	return dfs(l, 0, true)
}