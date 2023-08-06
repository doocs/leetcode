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

func maximumSafenessFactor(grid [][]int) int {
	n := len(grid)
	if grid[0][0] == 1 || grid[n-1][n-1] == 1 {
		return 0
	}
	q := [][2]int{}
	dist := make([][]int, n)
	const inf = 1 << 30
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				dist[i][j] = 0
				q = append(q, [2]int{i, j})
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == inf {
				dist[x][y] = dist[i][j] + 1
				q = append(q, [2]int{x, y})
			}
		}
	}
	t := [][3]int{}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			t = append(t, [3]int{dist[i][j], i, j})
		}
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][0] > t[j][0]
	})
	uf := newUnionFind(n * n)
	for _, p := range t {
		d, i, j := p[0], p[1], p[2]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d {
				uf.union(i*n+j, x*n+y)
			}
		}
		if uf.find(0) == uf.find(n*n-1) {
			return d
		}
	}
	return 0
}