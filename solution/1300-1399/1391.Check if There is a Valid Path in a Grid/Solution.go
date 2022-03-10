func hasValidPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	left := func(i, j int) {
		if j > 0 && (grid[i][j-1] == 1 || grid[i][j-1] == 4 || grid[i][j-1] == 6) {
			p[find(i*n+j)] = find(i*n + j - 1)
		}
	}
	right := func(i, j int) {
		if j < n-1 && (grid[i][j+1] == 1 || grid[i][j+1] == 3 || grid[i][j+1] == 5) {
			p[find(i*n+j)] = find(i*n + j + 1)
		}
	}
	up := func(i, j int) {
		if i > 0 && (grid[i-1][j] == 2 || grid[i-1][j] == 3 || grid[i-1][j] == 4) {
			p[find(i*n+j)] = find((i-1)*n + j)
		}
	}
	down := func(i, j int) {
		if i < m-1 && (grid[i+1][j] == 2 || grid[i+1][j] == 5 || grid[i+1][j] == 6) {
			p[find(i*n+j)] = find((i+1)*n + j)
		}
	}
	for i, row := range grid {
		for j, e := range row {
			if e == 1 {
				left(i, j)
				right(i, j)
			} else if e == 2 {
				up(i, j)
				down(i, j)
			} else if e == 3 {
				left(i, j)
				down(i, j)
			} else if e == 4 {
				right(i, j)
				down(i, j)
			} else if e == 5 {
				left(i, j)
				up(i, j)
			} else {
				right(i, j)
				up(i, j)
			}
		}
	}
	return find(0) == find(m*n-1)
}