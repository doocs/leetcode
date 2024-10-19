func numberOfWays(n int, x int, y int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, x+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 1; j <= x; j++ {
			f[i][j] = (f[i-1][j]*j%mod + f[i-1][j-1]*(x-(j-1))%mod) % mod
		}
	}
	ans, p := 0, 1
	for j := 1; j <= x; j++ {
		p = p * y % mod
		ans = (ans + f[n][j]*p%mod) % mod
	}
	return ans
}
