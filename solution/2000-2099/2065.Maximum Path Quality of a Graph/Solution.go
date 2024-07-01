func maximalPathQuality(values []int, edges [][]int, maxTime int) (ans int) {
	n := len(values)
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, t := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, t})
		g[v] = append(g[v], [2]int{u, t})
	}
	vis := make([]bool, n)
	vis[0] = true
	var dfs func(u, cost, value int)
	dfs = func(u, cost, value int) {
		if u == 0 {
			ans = max(ans, value)
		}
		for _, e := range g[u] {
			v, t := e[0], e[1]
			if cost+t <= maxTime {
				if vis[v] {
					dfs(v, cost+t, value)
				} else {
					vis[v] = true
					dfs(v, cost+t, value+values[v])
					vis[v] = false
				}
			}
		}
	}
	dfs(0, 0, values[0])
	return
}