func isBipartite(graph [][]int) bool {
	n := len(graph)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for u, g := range graph {
		for _, v := range g {
			if find(u) == find(v) {
				return false
			}
			p[find(v)] = find(g[0])
		}
	}
	return true
}