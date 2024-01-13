func findShortestCycle(n int, edges [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	const inf = 1 << 30
	bfs := func(u, v int) int {
		dist := make([]int, n)
		for i := range dist {
			dist[i] = inf
		}
		dist[u] = 0
		q := []int{u}
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if (i == u && j == v) || (i == v && j == u) || dist[j] != inf {
					continue
				}
				dist[j] = dist[i] + 1
				q = append(q, j)
			}
		}
		return dist[v] + 1
	}
	ans := inf
	for _, e := range edges {
		u, v := e[0], e[1]
		ans = min(ans, bfs(u, v))
	}
	if ans < inf {
		return ans
	}
	return -1
}