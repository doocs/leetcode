func treeDiameter(edges [][]int) int {
	n := len(edges)
	g := make(map[int][]int)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
		g[e[1]] = append(g[e[1]], e[0])
	}
	vis := make(map[int]bool, n+1)
	ans := 0
	next := edges[0][0]
	var dfs func(u, t int)
	dfs = func(u, t int) {
		if vis[u] {
			return
		}
		vis[u] = true
		if ans < t {
			ans = t
			next = u
		}
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v, t+1)
			}
		}
	}
	dfs(next, 0)
	vis = make(map[int]bool, n+1)
	dfs(next, 0)
	return ans
}