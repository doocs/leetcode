func hitBricks(grid [][]int, hits [][]int) []int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n+1)
	size := make([]int, len(p))
	for i := range p {
		p[i] = i
		size[i] = 1
	}

	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa != pb {
			size[pb] += size[pa]
			p[pa] = pb
		}
	}

	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = grid[i][j]
		}
	}
	for _, h := range hits {
		g[h[0]][h[1]] = 0
	}
	for j, v := range g[0] {
		if v == 1 {
			union(j, m*n)
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if g[i][j] == 0 {
				continue
			}
			if g[i-1][j] == 1 {
				union(i*n+j, (i-1)*n+j)
			}
			if j > 0 && g[i][j-1] == 1 {
				union(i*n+j, i*n+j-1)
			}
		}
	}
	ans := make([]int, len(hits))
	dirs := []int{-1, 0, 1, 0, -1}
	for k := len(hits) - 1; k >= 0; k-- {
		i, j := hits[k][0], hits[k][1]
		if grid[i][j] == 0 {
			continue
		}
		g[i][j] = 1
		prev := size[find(m*n)]
		if i == 0 {
			union(j, m*n)
		}
		for l := 0; l < 4; l++ {
			x, y := i+dirs[l], j+dirs[l+1]
			if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1 {
				union(i*n+j, x*n+y)
			}
		}
		curr := size[find(m*n)]
		ans[k] = max(0, curr-prev-1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}