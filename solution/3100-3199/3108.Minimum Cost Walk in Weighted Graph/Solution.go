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

func (uf *unionFind) getSize(x int) int {
	return uf.size[uf.find(x)]
}

func minimumCost(n int, edges [][]int, query [][]int) (ans []int) {
	uf := newUnionFind(n)
	g := make([]int, n)
	for i := range g {
		g[i] = -1
	}
	for _, e := range edges {
		uf.union(e[0], e[1])
	}
	for _, e := range edges {
		root := uf.find(e[0])
		g[root] &= e[2]
	}
	f := func(u, v int) int {
		if u == v {
			return 0
		}
		a, b := uf.find(u), uf.find(v)
		if a == b {
			return g[a]
		}
		return -1
	}
	for _, q := range query {
		ans = append(ans, f(q[0], q[1]))
	}
	return
}