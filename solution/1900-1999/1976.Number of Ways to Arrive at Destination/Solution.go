func countPaths(n int, roads [][]int) int {
	const inf = math.MaxInt64 / 2
	const mod = int(1e9) + 7
	g := make([][]int, n)
	dist := make([]int, n)
	w := make([]int, n)
	vis := make([]bool, n)
	for i := range g {
		g[i] = make([]int, n)
		dist[i] = inf
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, r := range roads {
		u, v, t := r[0], r[1], r[2]
		g[u][v], g[v][u] = t, t
	}
	g[0][0] = 0
	dist[0] = 0
	w[0] = 1
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			if j == t {
				continue
			}
			ne := dist[t] + g[t][j]
			if dist[j] > ne {
				dist[j] = ne
				w[j] = w[t]
			} else if dist[j] == ne {
				w[j] = (w[j] + w[t]) % mod
			}
		}
	}
	return w[n-1]
}