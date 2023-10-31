func minimumDistance(n int, edges [][]int, s int, marked []int) int {
	const inf = 1 << 29
	g := make([][]int, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
		dist[i] = inf
	}
	dist[s] = 0
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u][v] = min(g[u][v], w)
	}
	vis := make([]bool, n)
	for _ = range g {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[j] < dist[t]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+g[t][j])
		}
	}
	ans := inf
	for _, i := range marked {
		ans = min(ans, dist[i])
	}
	if ans >= inf {
		return -1
	}
	return ans
}