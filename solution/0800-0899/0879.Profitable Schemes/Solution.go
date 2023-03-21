func profitableSchemes(n int, minProfit int, group []int, profit []int) int {
	m := len(group)
	f := make([][][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, minProfit+1)
		}
	}
	for j := 0; j <= n; j++ {
		f[0][j][0] = 1
	}
	const mod = 1e9 + 7
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			for k := 0; k <= minProfit; k++ {
				f[i][j][k] = f[i-1][j][k]
				if j >= group[i-1] {
					f[i][j][k] += f[i-1][j-group[i-1]][max(0, k-profit[i-1])]
					f[i][j][k] %= mod
				}
			}
		}
	}
	return f[m][n][minProfit]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}