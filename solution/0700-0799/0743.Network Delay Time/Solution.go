func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 1 << 29
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, e := range times {
		g[e[0]-1][e[1]-1] = e[2]
	}

	dist := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	dist[k-1] = 0

	vis := make([]bool, n)
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+g[t][j])
		}
	}

	if ans := slices.Max(dist); ans != inf {
		return ans
	}
	return -1
}
