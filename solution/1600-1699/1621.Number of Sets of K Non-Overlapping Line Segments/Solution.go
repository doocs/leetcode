func numberOfSets(n int, k int) int {
	f := make([][]int, n+1)
	g := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		g[i] = make([]int, k+1)
	}
	f[1][0] = 1
	var mod int = 1e9 + 7
	for i := 2; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j] = (f[i-1][j] + g[i-1][j]) % mod
			g[i][j] = g[i-1][j]
			if j > 0 {
				g[i][j] += f[i-1][j-1]
				g[i][j] %= mod
				g[i][j] += g[i-1][j-1]
				g[i][j] %= mod
			}
		}
	}
	return (f[n][k] + g[n][k]) % mod
}