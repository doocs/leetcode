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
	pa, pb := uf.find(a-1), uf.find(b-1)
	if pa == pb {
		return false
	}
	uf.p[pa] = pb
	uf.n--
	return true
}

func maxNumEdgesToRemove(n int, edges [][]int) int {
	ufa, ufb := newUnionFind(n), newUnionFind(n)
	ans := 0
	for _, e := range edges {
		if e[0] == 3 {
			if ufa.union(e[1], e[2]) {
				ufb.union(e[1], e[2])
			} else {
				ans++
			}
		}
	}
	for _, e := range edges {
		if (e[0] == 1 && !ufa.union(e[1], e[2])) || (e[0] == 2 && !ufb.union(e[1], e[2])) {
			ans++
		}
	}
	if ufa.n == 1 && ufb.n == 1 {
		return ans
	}
	return -1
}