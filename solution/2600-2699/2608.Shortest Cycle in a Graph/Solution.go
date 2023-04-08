func findShortestCycle(n int, edges [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	const inf = 1 << 30
	bfs := func(u int) int {
		dist := make([]int, n)
		for i := range dist {
			dist[i] = -1
		}
		dist[u] = 0
		q := [][2]int{{u, -1}}
		ans := inf
		for len(q) > 0 {
			p := q[0]
			u = p[0]
			fa := p[1]
			q = q[1:]
			for _, v := range g[u] {
				if dist[v] < 0 {
					dist[v] = dist[u] + 1
					q = append(q, [2]int{v, u})
				} else if v != fa {
					ans = min(ans, dist[u]+dist[v]+1)
				}
			}
		}
		return ans
	}
	ans := inf
	for i := 0; i < n; i++ {
		ans = min(ans, bfs(i))
	}
	if ans < inf {
		return ans
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}