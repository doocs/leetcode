func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := map[int][]int{}
	for _, e := range graph {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}
	vis := map[int]bool{start: true}
	var dfs func(int) bool
	dfs = func(u int) bool {
		if u == target {
			return true
		}
		for _, v := range g[u] {
			if !vis[v] {
				vis[v] = true
				if dfs(v) {
					return true
				}
			}
		}
		return false
	}
	return dfs(start)
}