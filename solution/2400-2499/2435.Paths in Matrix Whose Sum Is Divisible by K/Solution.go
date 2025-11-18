func numberOfPaths(grid [][]int, K int) int {
	const mod = 1e9 + 7
	m, n := len(grid), len(grid[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, K)
		}
	}
	f[0][0][grid[0][0]%K] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < K; k++ {
				k0 := ((k - grid[i][j]%K) + K) % K
				if i > 0 {
					f[i][j][k] = (f[i][j][k] + f[i-1][j][k0]) % mod
				}
				if j > 0 {
					f[i][j][k] = (f[i][j][k] + f[i][j-1][k0]) % mod
				}
			}
		}
	}
	return f[m-1][n-1][0]
}
