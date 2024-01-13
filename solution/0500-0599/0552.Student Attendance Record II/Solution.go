func checkRecord(n int) int {
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, 2)
		for j := range f[i] {
			f[i][j] = make([]int, 3)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	const mod = 1e9 + 7
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= n {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, 0)
		if j == 0 {
			ans = (ans + dfs(i+1, j+1, 0)) % mod
		}
		if k < 2 {
			ans = (ans + dfs(i+1, j, k+1)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, 0, 0)
}