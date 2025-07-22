func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n)
	for i := range f {
		f[i] = make([][2]int, k+1)
		for j := range f[i] {
			f[i][j] = [2]int{-1, -1}
		}
	}
	const mod int = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if j < 0 {
			return 0
		}
		if i >= n {
			if j == 0 {
				return 1
			}
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		a := cnt1 * dfs(i+1, j, 1) % mod
		b := cnt0 * dfs(i+1, j-(k&1^1), 0) % mod
		f[i][j][k] = (a + b) % mod
		return f[i][j][k]
	}
	return dfs(0, k, 1)
}
