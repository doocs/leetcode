func connectTwoGroups(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	const inf = 1 << 30
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, 1<<n)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= m; i++ {
		for j := 0; j < 1<<n; j++ {
			for k := 0; k < n; k++ {
				c := cost[i-1][k]
				if j>>k&1 == 1 {
					f[i][j] = min(f[i][j], f[i][j^(1<<k)]+c)
					f[i][j] = min(f[i][j], f[i-1][j]+c)
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+c)
				}
			}
		}
	}
	return f[m][(1<<n)-1]
}