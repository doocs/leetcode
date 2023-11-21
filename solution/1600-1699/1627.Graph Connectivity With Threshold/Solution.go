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

func areConnected(n int, threshold int, queries [][]int) []bool {
	uf := newUnionFind(n + 1)
	for a := threshold + 1; a <= n; a++ {
		for b := a + a; b <= n; b += a {
			uf.union(a, b)
		}
	}
	ans := make([]bool, len(queries))
	for i, q := range queries {
		ans[i] = uf.find(q[0]) == uf.find(q[1])
	}
	return ans
}