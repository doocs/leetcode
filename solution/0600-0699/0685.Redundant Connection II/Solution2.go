type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func findRedundantDirectedConnection(edges [][]int) []int {
	n := len(edges)
	ind := make([]int, n)
	for _, e := range edges {
		ind[e[1]-1]++
	}
	dup := []int{}
	for i, e := range edges {
		if ind[e[1]-1] == 2 {
			dup = append(dup, i)
		}
	}
	uf := newUnionFind(n)
	if len(dup) > 0 {
		for i, e := range edges {
			if i == dup[1] {
				continue
			}
			if !uf.union(e[0]-1, e[1]-1) {
				return edges[dup[0]]
			}
		}
		return edges[dup[1]]
	}
	for _, e := range edges {
		if !uf.union(e[0]-1, e[1]-1) {
			return e
		}
	}
	return nil
}
