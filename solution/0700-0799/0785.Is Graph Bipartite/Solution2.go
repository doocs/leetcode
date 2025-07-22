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
	for a, bs := range graph {
		for _, b := range bs {
			pa, pb := find(a), find(b)
			if pa == pb {
				return false
			}
			p[pb] = find(bs[0])
		}
	}
	return true
}
