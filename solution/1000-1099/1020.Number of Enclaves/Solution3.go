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

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	uf := newUnionFind(m*n + 1)
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				if i == 0 || i == m-1 || j == 0 || j == n-1 {
					uf.union(i*n+j, m*n)
				} else {
					for k := 0; k < 4; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
							uf.union(i*n+j, x*n+y)
						}
					}
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 && uf.find(i*n+j) != uf.find(m*n) {
				ans++
			}
		}
	}
	return
}