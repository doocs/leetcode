func numberOfSets(n int, maxDistance int, roads [][]int) (ans int) {
	for mask := 0; mask < 1<<n; mask++ {
		g := make([][]int, n)
		for i := range g {
			g[i] = make([]int, n)
			for j := range g[i] {
				g[i][j] = 1 << 29
			}
		}
		for _, e := range roads {
			u, v, w := e[0], e[1], e[2]
			if mask>>u&1 == 1 && mask>>v&1 == 1 {
				g[u][v] = min(g[u][v], w)
				g[v][u] = min(g[v][u], w)
			}
		}
		for k := 0; k < n; k++ {
			if mask>>k&1 == 1 {
				g[k][k] = 0
				for i := 0; i < n; i++ {
					for j := 0; j < n; j++ {
						g[i][j] = min(g[i][j], g[i][k]+g[k][j])
					}
				}
			}
		}
		ok := 1
		for i := 0; i < n && ok == 1; i++ {
			for j := 0; j < n && ok == 1; j++ {
				if mask>>i&1 == 1 && mask>>j&1 == 1 && g[i][j] > maxDistance {
					ok = 0
				}
			}
		}
		ans += ok
	}
	return
}