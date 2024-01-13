func possibleBipartition(n int, dislikes [][]int) bool {
	p := make([]int, n)
	g := make([][]int, n)
	for i := range p {
		p[i] = i
	}
	for _, e := range dislikes {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(g[i][0])
		}
	}
	return true
}