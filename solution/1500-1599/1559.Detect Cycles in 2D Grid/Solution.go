func containsCycle(grid [][]byte) bool {
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
	dirs := []int{1, 0, 1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < 2; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x < m && y < n && grid[x][y] == grid[i][j] {
					if find(x*n+y) == find(i*n+j) {
						return true
					}
					p[find(x*n+y)] = find(i*n + j)
				}
			}
		}
	}
	return false
}