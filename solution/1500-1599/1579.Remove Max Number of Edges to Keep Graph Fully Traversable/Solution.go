type unionFind struct {
	p, size []int
	cnt     int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size, n}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a-1), uf.find(b-1)
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
	uf.cnt--
	return true
}

func maxNumEdgesToRemove(n int, edges [][]int) (ans int) {
	ufa := newUnionFind(n)
	ufb := newUnionFind(n)
	for _, e := range edges {
		t, u, v := e[0], e[1], e[2]
		if t == 3 {
			if ufa.union(u, v) {
				ufb.union(u, v)
			} else {
				ans++
			}
		}
	}
	for _, e := range edges {
		t, u, v := e[0], e[1], e[2]
		if t == 1 && !ufa.union(u, v) {
			ans++
		}
		if t == 2 && !ufb.union(u, v) {
			ans++
		}
	}
	if ufa.cnt == 1 && ufb.cnt == 1 {
		return
	}
	return -1
}