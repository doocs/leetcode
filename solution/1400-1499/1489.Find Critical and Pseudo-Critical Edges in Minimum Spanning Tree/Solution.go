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

func findCriticalAndPseudoCriticalEdges(n int, edges [][]int) [][]int {
	for i := range edges {
		edges[i] = append(edges[i], i)
	}
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][2] < edges[j][2]
	})
	v := 0
	uf := newUnionFind(n)
	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		if uf.union(f, t) {
			v += w
		}
	}
	ans := make([][]int, 2)
	for _, e := range edges {
		f, t, w, i := e[0], e[1], e[2], e[3]
		uf = newUnionFind(n)
		k := 0
		for _, ne := range edges {
			x, y, z, j := ne[0], ne[1], ne[2], ne[3]
			if j != i && uf.union(x, y) {
				k += z
			}
		}
		if uf.n > 1 || (uf.n == 1 && k > v) {
			ans[0] = append(ans[0], i)
			continue
		}
		uf = newUnionFind(n)
		uf.union(f, t)
		k = w
		for _, ne := range edges {
			x, y, z, j := ne[0], ne[1], ne[2], ne[3]
			if j != i && uf.union(x, y) {
				k += z
			}
		}
		if k == v {
			ans[1] = append(ans[1], i)
		}
	}
	return ans
}