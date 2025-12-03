func leadsToDestination(n int, edges [][]int, source int, destination int) bool {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	if len(g[destination]) > 0 {
		return false
	}

	st := make([]int, n)

	var dfs func(i int) bool
	dfs = func(i int) bool {
		if st[i] != 0 {
			return st[i] == 2
		}
		if len(g[i]) == 0 {
			return i == destination
		}
		st[i] = 1
		for _, j := range g[i] {
			if !dfs(j) {
				return false
			}
		}
		st[i] = 2
		return true
	}

	return dfs(source)
}
