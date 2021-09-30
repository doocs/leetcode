var p []int

func closedIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				continue
			}
			idx := i*n + j
			if i < m-1 && grid[i+1][j] == 0 {
				p[find(idx)] = find((i+1)*n + j)
			}
			if j < n-1 && grid[i][j+1] == 0 {
				p[find(idx)] = find(i*n + j + 1)
			}
		}
	}
	s := make([]bool, m*n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				s[find(i*n+j)] = true
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			root := find(i*n + j)
			if !s[root] {
				continue
			}
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				s[root] = false
			}
		}
	}
	res := 0
	for _, e := range s {
		if e {
			res++
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}