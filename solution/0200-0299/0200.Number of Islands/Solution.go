var p []int

func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				if i < m-1 && grid[i+1][j] == '1' {
					p[find(i*n+j)] = find((i+1)*n + j)
				}
				if j < n-1 && grid[i][j+1] == '1' {
					p[find(i*n+j)] = find(i*n + j + 1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' && i*n+j == find(i*n+j) {
				res++
			}
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