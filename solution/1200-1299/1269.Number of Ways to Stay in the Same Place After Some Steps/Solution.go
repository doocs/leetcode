func numWays(steps int, arrLen int) int {
	const mod int = 1e9 + 7
	f := make([][]int, steps)
	for i := range f {
		f[i] = make([]int, steps+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) (ans int) {
		if i > j || i >= arrLen || i < 0 || j < 0 {
			return 0
		}
		if i == 0 && j == 0 {
			return 1
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		for k := -1; k <= 1; k++ {
			ans += dfs(i+k, j-1)
			ans %= mod
		}
		f[i][j] = ans
		return
	}
	return dfs(0, steps)
}
