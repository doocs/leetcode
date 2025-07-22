func keyboard(k int, n int) int {
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, k+1)
		c[i][0] = 1
	}
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod
		}
	}
	f := make([][27]int, n+1)
	for j := range f[0] {
		f[0][j] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j < 27; j++ {
			for h := 0; h <= min(i, k); h++ {
				f[i][j] = (f[i][j] + f[i-h][j-1]*c[i][h]%mod) % mod
			}
		}
	}
	return f[n][26]
}
