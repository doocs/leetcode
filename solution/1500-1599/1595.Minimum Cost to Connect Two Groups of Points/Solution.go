func connectTwoGroups(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	const inf = 1 << 30
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0
	g := make([]int, 1<<n)
	for i := 1; i <= m; i++ {
		for j := 0; j < 1<<n; j++ {
			g[j] = inf
			for k := 0; k < n; k++ {
				c := cost[i-1][k]
				if j>>k&1 == 1 {
					g[j] = min(g[j], g[j^1<<k]+c)
					g[j] = min(g[j], f[j]+c)
					g[j] = min(g[j], f[j^1<<k]+c)
				}
			}
		}
		copy(f, g)
	}
	return f[1<<n-1]
}