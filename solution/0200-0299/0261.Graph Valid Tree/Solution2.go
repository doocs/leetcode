func validTree(n int, edges [][]int) bool {
	if len(edges) != n-1 {
		return false
	}
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		n--
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}
	dfs(0)
	return n == 0
}