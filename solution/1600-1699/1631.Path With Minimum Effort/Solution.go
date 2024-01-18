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

func (uf *unionFind) connected(a, b int) bool {
	return uf.find(a) == uf.find(b)
}

func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	edges := make([][3]int, 0, m*n*2)
	dirs := [3]int{0, 1, 0}
	for i, row := range heights {
		for j, h := range row {
			for k := 0; k < 2; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					edges = append(edges, [3]int{abs(h - heights[x][y]), i*n + j, x*n + y})
				}
			}
		}
	}
	sort.Slice(edges, func(i, j int) bool { return edges[i][0] < edges[j][0] })
	uf := newUnionFind(m * n)
	for _, e := range edges {
		uf.union(e[1], e[2])
		if uf.connected(0, m*n-1) {
			return e[0]
		}
	}
	return 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}