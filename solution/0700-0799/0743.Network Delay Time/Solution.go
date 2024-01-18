func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	vis := make([]bool, n)
	g := make([][]int, n)
	for i := range dist {
		dist[i] = inf
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, t := range times {
		g[t[0]-1][t[1]-1] = t[2]
	}
	dist[k-1] = 0
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
	ans := slices.Max(dist)
	if ans == inf {
		return -1
	}
	return ans
}