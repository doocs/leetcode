func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n+1)
	for i := range f {
		f[i] = make([][2]int, k+1)
	}
	f[0][0][1] = 1
	cnt0 := m / 2
	cnt1 := m - cnt0
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j][0] = cnt0 * f[i-1][j][1] % mod
			if j > 0 {
				f[i][j][0] = (f[i][j][0] + cnt0*f[i-1][j-1][0]%mod) % mod
			}
			f[i][j][1] = cnt1 * (f[i-1][j][0] + f[i-1][j][1]) % mod
		}
	}
	return (f[n][k][0] + f[n][k][1]) % mod
}
