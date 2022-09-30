func leadsToDestination(n int, edges [][]int, source int, destination int) bool {
	vis := make([]bool, n)
	g := make([][]int, n)
	f := make([]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == destination {
			return len(g[i]) == 0
		}
		if f[i] != 0 {
			return f[i] == 1
		}
		if vis[i] || len(g[i]) == 0 {
			return false
		}
		vis[i] = true
		for _, j := range g[i] {
			if !dfs(j) {
				f[i] = -1
				return false
			}
		}
		f[i] = 1
		return true
	}
	return dfs(source)
}