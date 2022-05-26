type unionFind struct {
	p []int
	n int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	return &unionFind{p, n}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	if uf.find(a) == uf.find(b) {
		return false
	}
	uf.p[uf.find(a)] = uf.find(b)
	uf.n--
	return true
}

func findRedundantDirectedConnection(edges [][]int) []int {
	n := len(edges)
	p := make([]int, n+1)
	for i := range p {
		p[i] = i
	}
	uf := newUnionFind(n + 1)
	conflict, cycle := -1, -1
	for i, e := range edges {
		u, v := e[0], e[1]
		if p[v] != v {
			conflict = i
		} else {
			p[v] = u
			if !uf.union(u, v) {
				cycle = i
			}
		}
	}
	if conflict == -1 {
		return edges[cycle]
	}
	v := edges[conflict][1]
	if cycle != -1 {
		return []int{p[v], v}
	}
	return edges[conflict]
}