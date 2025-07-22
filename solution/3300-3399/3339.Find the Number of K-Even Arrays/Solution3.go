func countOfArrays(n int, m int, k int) int {
	const mod = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	f := make([][2]int, k+1)
	f[0][1] = 1

	for i := 0; i < n; i++ {
		g := make([][2]int, k+1)
		for j := 0; j <= k; j++ {
			g[j][0] = (cnt0 * (f[j][1] + func() int {
				if j > 0 {
					return f[j-1][0]
				}
				return 0
			}()) % mod) % mod
			g[j][1] = (cnt1 * (f[j][0] + f[j][1]) % mod) % mod
		}
		f = g
	}

	return (f[k][0] + f[k][1]) % mod
}
