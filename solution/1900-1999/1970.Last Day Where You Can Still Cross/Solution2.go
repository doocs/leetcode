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

func latestDayToCross(row int, col int, cells [][]int) int {
	mn := len(cells)
	uf := newUnionFind(mn + 2)
	s, t := mn, mn+1
	g := make([][]int, row)
	for i := range g {
		g[i] = make([]int, col)
		for j := range g[i] {
			g[i][j] = 1
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := mn - 1; ; i-- {
		x, y := cells[i][0]-1, cells[i][1]-1
		g[x][y] = 0
		for j := 0; j < 4; j++ {
			nx, ny := x+dirs[j], y+dirs[j+1]
			if nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] == 0 {
				uf.union(x*col+y, nx*col+ny)
			}
		}
		if x == 0 {
			uf.union(s, x*col+y)
		}
		if x == row-1 {
			uf.union(t, x*col+y)
		}
		if uf.find(s) == uf.find(t) {
			return i
		}
	}
}
