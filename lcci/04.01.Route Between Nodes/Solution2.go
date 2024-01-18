func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := map[int][]int{}
	for _, e := range graph {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}
	q := []int{start}
	vis := map[int]bool{start: true}
	for len(q) > 0 {
		u := q[0]
		if u == target {
			return true
		}
		q = q[1:]
		for _, v := range g[u] {
			if !vis[v] {
				vis[v] = true
				q = append(q, v)
			}
		}
	}
	return false
}