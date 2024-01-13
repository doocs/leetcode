func profitableSchemes(n int, minProfit int, group []int, profit []int) int {
	m := len(group)
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, minProfit+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	const mod = 1e9 + 7
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= m {
			if k >= minProfit {
				return 1
			}
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, k)
		if j+group[i] <= n {
			ans += dfs(i+1, j+group[i], min(k+profit[i], minProfit))
		}
		ans %= mod
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, 0, 0)
}