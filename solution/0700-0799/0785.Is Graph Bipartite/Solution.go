var p []int

func isBipartite(graph [][]int) bool {
	n := len(graph)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
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

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}