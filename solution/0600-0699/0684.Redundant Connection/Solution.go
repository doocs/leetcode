func findRedundantConnection(edges [][]int) []int {
	p := make([]int, 1010)
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
	for _, e := range edges {
		a, b := e[0], e[1]
		if find(a) == find(b) {
			return e
		}
		p[find(a)] = find(b)
	}
	return []int{}
}