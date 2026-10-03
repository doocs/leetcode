func validPath(n int, edges [][]int, source int, destination int) bool {
	if source == destination {
		return true
	}
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	vis := make([]bool, n)
	vis[source] = true
	stk := []int{source}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, j := range g[i] {
			if j == destination {
				return true
			}
			if !vis[j] {
				vis[j] = true
				stk = append(stk, j)
			}
		}
	}
	return false
}
