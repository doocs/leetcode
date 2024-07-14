func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range graph {
		g[e[0]] = append(g[e[0]], e[1])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == target {
			return true
		}
		if vis[i] {
			return false
		}
		vis[i] = true
		for _, j := range g[i] {
			if dfs(j) {
				return true
			}
		}
		return false
	}
	return dfs(start)
}