func numberOfWays(corridor string) int {
	n := len(corridor)
	f := make([][3]int, n)
	for i := range f {
		f[i] = [3]int{-1, -1, -1}
	}
	const mod = 1e9 + 7
	var dfs func(int, int) int
	dfs = func(i, k int) int {
		if i >= n {
			if k == 2 {
				return 1
			}
			return 0
		}
		if f[i][k] != -1 {
			return f[i][k]
		}
		if corridor[i] == 'S' {
			k++
		}
		if k > 2 {
			return 0
		}
		f[i][k] = dfs(i+1, k)
		if k == 2 {
			f[i][k] = (f[i][k] + dfs(i+1, 0)) % mod
		}
		return f[i][k]
	}
	return dfs(0, 0)
}
