func largestArea(grid []string) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n+1)
	size := make([]int, m*n+1)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	dirs := []int{-1, 0, 1, 0, -1}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || i == m-1 || j == 0 || j == n-1 || grid[i][j] == '0' {
				p[find(i*n+j)] = find(m * n)
			} else {
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if (grid[x][y] == '0' || grid[i][j] == grid[x][y]) && find(x*n+y) != find(i*n+j) {
						size[find(x*n+y)] += size[find(i*n+j)]
						p[find(i*n+j)] = find(x*n + y)
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if find(i*n+j) != find(m*n) && ans < size[i*n+j] {
				ans = size[i*n+j]
			}
		}
	}
	return ans
}